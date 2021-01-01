package i.design.modules.users.models.output

data class LoginStatusModel(
    val name: String,
    val image: String,
    val token: String,
    // 脱敏后的 Email
    val email: String
)
