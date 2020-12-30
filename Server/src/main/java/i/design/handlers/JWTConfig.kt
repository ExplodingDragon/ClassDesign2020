package i.design.handlers

import i.design.handlers.jwt.AuthInterceptor
import i.design.handlers.jwt.UserIdArgumentResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class JWTConfig : WebMvcConfigurer {
    @Autowired
    private lateinit var authInterceptor: AuthInterceptor

    @Autowired
    private lateinit var userInfoHandlerMethodArgumentResolver: UserIdArgumentResolver

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver?>) {
        argumentResolvers.add(userInfoHandlerMethodArgumentResolver)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/**")
        super.addInterceptors(registry)
    }
}