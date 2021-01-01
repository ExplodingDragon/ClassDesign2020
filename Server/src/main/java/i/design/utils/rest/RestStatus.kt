package i.design.utils.rest

data class RestStatus<T : Any>(
    val staus: Boolean,
    val id: T
)
