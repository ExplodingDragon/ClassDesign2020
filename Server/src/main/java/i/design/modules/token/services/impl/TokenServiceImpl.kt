package i.design.modules.token.services.impl

import com.github.openEdgn.logger4k.getLogger
import com.github.open_edgn.security4k.asymmetric.rsa.RsaPrivate
import com.github.open_edgn.security4k.asymmetric.rsa.RsaPublic
import i.design.handlers.exceptions.ApplicationExceptions
import i.design.handlers.jwt.AuthInterceptor
import i.design.handlers.jwt.RSAKeyPair
import i.design.modules.token.services.ITokenService
import i.design.modules.user.models.TokenInfo
import i.design.modules.user.models.table.UserEntity
import i.design.modules.user.repository.UserRepository
import i.design.utils.SnowFlake
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.web.context.request.WebRequest
import java.util.concurrent.TimeUnit
import javax.annotation.Resource


@Service("token")
class TokenServiceImpl : ITokenService<TokenInfo> {

    @Value("\${app.redis.timeOut}")
    private var redisTimeOut: Long = -1

    override val logger = getLogger()

    @Autowired
    private lateinit var snowFlake: SnowFlake

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @Autowired
    private lateinit var rsaKeyPair: RSAKeyPair

    private val rsaPublic by lazy {
        RsaPublic(rsaKeyPair.publicKey)
    }

    private val rsaPrivate by lazy {
        RsaPrivate(rsaKeyPair.privateKey)
    }

    @Resource
    private lateinit var userRepository: UserRepository

    override fun verifyToken(token: String, tokenInfo: TokenInfo): Boolean {
        val userIdStr =
            redisTemplate.opsForHash<String, String>()
                .get(rsaPrivate.decodeText(token), KEY_USER_ID) ?: return false
        redisTemplate.expire(token, redisTimeOut, TimeUnit.DAYS)
        val userId = userIdStr.toLong()
        val users = userRepository.findById(userId)
        return !users.isEmpty
    }

    override fun createToken(user: UserEntity): String {
        val key = snowFlake.nextId().toString()
        val encodeKey = rsaPublic.encodeText(key)
        redisTemplate.opsForHash<String, String>().run {
            put(key, KEY_USER_ID, user.id.toString())
            redisTemplate.expire(key, redisTimeOut, TimeUnit.DAYS)
        }
        return encodeKey.replace(Regex("[\r\n]"), "")
    }

    override fun verifyAnnotation() = TokenInfo::class

    override fun loadUserIdFromToken(webRequest: WebRequest): Long {
        return (redisTemplate.opsForHash<String, String>()
            .get(rsaPrivate.decodeText(AuthInterceptor.getHeader(webRequest)), KEY_USER_ID)
            ?: throw ApplicationExceptions.forbidden("")).toLong()
    }

    companion object {
        const val KEY_USER_ID = "userId"
    }
}