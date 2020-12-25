package i.design.handlers

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import javax.annotation.PostConstruct
import javax.annotation.Resource


@EnableWebMvc
@Configuration
class ExceptionHandleConfig {
    @Resource
    private lateinit var dispatcherServlet: DispatcherServlet

    @PostConstruct
    private fun configureDispatcherServlet() {
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true)
    }
}