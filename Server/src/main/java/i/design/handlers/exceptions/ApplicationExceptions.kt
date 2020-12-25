package i.design.handlers.exceptions

import org.springframework.http.HttpStatus

object ApplicationExceptions {
    fun httpRequestException(httpStatus: HttpStatus): ApplicationException =
        ApplicationException(httpStatus.value(), httpStatus.reasonPhrase)

    fun badRequest(msg: String = "Bad Request!"): ApplicationException {
        return ApplicationException(400, msg)
    }

    fun internalException(msg: String = "未知错误！"): ApplicationException = ApplicationException(500, msg)
}