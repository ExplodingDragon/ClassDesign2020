package i.design.modules.token.services.impl

import com.github.openEdgn.logger4k.getLogger
import i.design.handlers.exceptions.ApplicationExceptions
import i.design.modules.auth.models.TokenInfo
import i.design.modules.token.models.TokenItem
import i.design.modules.token.services.ITokenService
import i.design.modules.token.utils.TokenUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("token")
class TokenServiceImpl : ITokenService<TokenInfo> {
    private val logger = getLogger()

    @Autowired
    private lateinit var tokenUtils: TokenUtils

    override fun verifyToken(token: String, tokenInfo: TokenInfo): Boolean {
        val tokenItem: TokenItem = try {
            tokenUtils.decode(token)
        } catch (e: Exception) {
            logger.error("token解码失败 : [{}]", e.message)
            throw ApplicationExceptions.forbidden("")
        }
        return true
    }

    override fun verifyAnnotation() = TokenInfo::class
}