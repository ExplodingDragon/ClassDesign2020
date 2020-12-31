package i.design.modules.token.services.impl


import com.github.openEdgn.logger4k.getLogger
import i.design.modules.token.models.annotations.Token
import i.design.modules.token.services.ITokenService
import org.springframework.stereotype.Service
import org.springframework.web.context.request.WebRequest


@Service("token")
class TokenServiceImpl : ITokenService<Token> {
    override val logger = getLogger()

    override fun verifyToken(token: String, tokenInfo: Token): Boolean {
        TODO("Not yet implemented")
    }


    override fun getUserIdByToken(token: String): Long {
        TODO("Not yet implemented")
    }

    override val verifyAnnotation = Token::class
}