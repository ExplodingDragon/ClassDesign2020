package i.design.handlers

import com.github.openEdgn.logger4k.getLogger
import i.design.handlers.exceptions.ApplicationException
import i.design.handlers.result.Result
import i.design.handlers.result.Results
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class ExceptionHandler {

    private val logger = getLogger()

    @ResponseBody
    @ExceptionHandler(value = [Exception::class])
    @ResponseStatus
    fun exceptionHandler(e: Exception): Result {
        logger.errorThrowable("发送服务器内部错误", e)
        return Results.Fail.UNKNOWN("服务器内部错误")
    }

    @ExceptionHandler(
        NoHandlerFoundException::class
    )
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    fun notFoundExceptionHandler(e: NoHandlerFoundException): Result {
        return Results.Fail.NOT_FOUND(e.requestURL)
    }

    @ResponseBody
    @ExceptionHandler(value = [ApplicationException::class])
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun formatCheckExceptionHandler(e: ApplicationException): Result {
        logger.warn("触发错误：{} ", e.message)
        return Result(e.errorId, e.errorMessage)
    }

    @ResponseBody
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun formatCheckExceptionHandler(e: MethodArgumentNotValidException): Result {
        logger.warn("客户端请求错误：{} ", e.message)
        val builder = StringBuilder()
        e.bindingResult.allErrors.forEach { it ->
            val param = if (it is FieldError) {
                it.field
            } else {
                ""
            }
            builder.append("字段 $param ").append(it.defaultMessage ?: "格式化异常")
        }
        return Results.Fail.BAD_REQUEST(builder.toString())
    }

}