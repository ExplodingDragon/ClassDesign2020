package i.design.modules.users.models.rest

data class UserModel(
    val id: Long,
    val email: String,
    val sex: String,
    val name: String,
    val image: String,
    val password: String = "",
    val admin: Boolean = false,
    val canLogin: Boolean
)
