package i.design.modules.token.services.impl


import com.github.openEdgn.logger4k.getLogger
import com.github.open_edgn.security4k.hash.sha256Sum
import i.design.handlers.exceptions.ApplicationExceptions
import i.design.modules.token.models.annotations.Token
import i.design.modules.token.services.ITokenService
import i.design.modules.users.services.IUserService
import i.design.utils.SnowFlake
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import javax.annotation.Resource


@Service("token")
class TokenServiceImpl : ITokenService<Token> {
    override val logger = getLogger()

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, String>

    @Resource
    private lateinit var userService: IUserService

    @Resource
    private lateinit var snowFlake: SnowFlake

    override fun verifyToken(token: String, tokenInfo: Token): Boolean {
        val tokenData = token.substring("Bearer".length).trim()
        val key = "auth_$tokenData"
        val userCache = redisTemplate.opsForHash<String, String>().get(key, "userId") ?: return false
        redisTemplate.expire(key, 1, TimeUnit.DAYS)
        // 更新过期时间
        return if (tokenInfo.admin) {
            val selectOneById = userService.selectOneById(userCache.toLong())
            return selectOneById.admin
        } else {
            true
        }
    }

    override fun getUserIdByToken(token: String): Long {
        val substring = token.substring("Bearer".length)
        val tokenData = substring.trim()
        val userCache = redisTemplate.opsForHash<String, String>().get("auth_$tokenData", "userId")
            ?: throw ApplicationExceptions.forbidden("")
        return userCache.toLong()

    }

    override fun createTokenByUserId(id: Long): String {
        val token = snowFlake.nextId().toString().sha256Sum()
        val key = "auth_$token"
        redisTemplate.opsForHash<String, String>().put(
            key, "userId", id.toString()
        )
        redisTemplate.expire(key, 1, TimeUnit.DAYS)
        return "Bearer $token"
    }

    override val verifyAnnotation = Token::class
}