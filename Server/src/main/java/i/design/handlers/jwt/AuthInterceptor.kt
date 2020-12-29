package i.design.handlers.jwt

import i.design.handlers.exceptions.ApplicationExceptions
import i.design.modules.token.services.ITokenService
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *  token 鉴权
 *
 */
@Component
class AuthInterceptor : HandlerInterceptor {

    @Resource(name = "token")
    private lateinit var tokenService: ITokenService<out Annotation>

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        val method = handler.method
        if (method.isAnnotationPresent(IgnoreToken::class.java)) {
            return true
        }

        val annotation = tokenService.verifyAnnotation().java
        return if (method.isAnnotationPresent(annotation)) {
            tokenService.verifyToken0(getToken(request), method.getAnnotation(annotation))
        } else {
            throw ApplicationExceptions.forbidden(request.requestURI)
        }
    }

    private fun getToken(request: HttpServletRequest): String {
        return request.getHeader("Authorization") ?: throw ApplicationExceptions.forbidden(request.requestURI)
    }
}