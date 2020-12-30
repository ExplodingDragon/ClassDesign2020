package i.design.modules.user.models.out

data class LoginStatusModel(
    val name: String,
    val token: String,
    val expired:Boolean
)
