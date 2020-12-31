package i.design.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.openEdgn.logger4k.getLogger
import i.design.handlers.result.Result
import i.design.handlers.result.ResultUtils
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice


@RestControllerAdvice
class ResponseAdvice : ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return returnType.parameterType != String::class.java
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        val requestPath = request.uri.path
        val result = if (body == null) {
            ResultUtils.Fail.nullPoint()
        } else {
            if (!requestPath.startsWith("/api/")) {
                formatData(body)
            } else {
                if (body is Result) {
                    formatData(body)
                } else {
                    ResultUtils.ok(formatData(body))

                }
            }
        }
        if (result is Result) {
            response.setStatusCode(HttpStatus.valueOf(result.code))
        }
        return result
    }

    private fun formatData(body: Any): Any {
        return if (body.javaClass == String::class.java) {
            val objectMapper = ObjectMapper()
            objectMapper.writeValueAsString(body)
            return objectMapper
        } else {
            body
        }
    }

}