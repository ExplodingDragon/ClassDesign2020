package i.design.handlers.exceptions

class ApplicationException(val errorId: Int, val errorMessage: String = "未知错误！") : RuntimeException(errorMessage) {
}