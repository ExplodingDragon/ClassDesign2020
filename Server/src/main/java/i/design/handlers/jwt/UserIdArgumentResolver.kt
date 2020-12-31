package i.design.handlers.jwt

import i.design.modules.token.services.ITokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer


/**
 * JWT 注入用户信息
 */
@Component
class UserIdArgumentResolver : HandlerMethodArgumentResolver {


    @Autowired
    private lateinit var tokenService: ITokenService<*>

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return (parameter.parameterType.isAssignableFrom(Long::class.java) ||
                parameter.parameterType.isAssignableFrom(Long::class.javaObjectType)) &&
                parameter.hasParameterAnnotation(UserId::class.java)
    }


    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Long {
        return tokenService.getUserIdByToken(AuthInterceptor.getHeader(webRequest))
    }
}