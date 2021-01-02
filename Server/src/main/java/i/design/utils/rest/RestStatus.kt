package i.design.utils.rest

data class RestStatus<T : Any>(
    val status: Boolean,
    val data: T
)
