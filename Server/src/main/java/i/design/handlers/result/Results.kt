package i.design.handlers.result

import i.design.handlers.result.Result
import org.springframework.http.HttpStatus

object Results {
    fun OK(any: Any): Result = Result(HttpStatus.OK.value(), HttpStatus.OK.name, any)

    object Fail {
        fun NULL(msg: String = "程序内部错误") = Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null)
        fun UNKNOWN(message: String) = Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null)
        fun BAD_REQUEST(msg: String) = Result(HttpStatus.BAD_REQUEST.value(), msg, null)
        fun NOT_FOUND(path: String) = Result(HttpStatus.NOT_FOUND.value(), "path not found: $path ")
    }
}