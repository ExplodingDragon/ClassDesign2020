package i.design.handlers

import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig : WebMvcConfigurer {
    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
    }

//        converters.add(0, MappingJackson2HttpMessageConverter())

//    override fun addInterceptors(registry: InterceptorRegistry) {
//        registry.addInterceptor(ErrorInterceptor()).addPathPatterns("/api/**")
//        super.addInterceptors(registry)
//    }
}