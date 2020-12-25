package i.design.handlers

import i.design.handlers.exceptions.ApplicationException
import i.design.handlers.result.Result
import i.design.handlers.result.Results
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class ExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = [Exception::class])
    @ResponseStatus
    fun exceptionHandler(e: Exception): Result {
        return Results.Fail.UNKNOWN(e.message ?: "未知错误！")
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
        return Result(e.errorId, e.errorMessage)
    }

    @ResponseBody
    @ExceptionHandler(value = [RuntimeException::class])
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun formatCheckExceptionHandler(e: RuntimeException): Result {
        return Results.Fail.BAD_REQUEST(e.message ?: "未知错误！")
    }

}