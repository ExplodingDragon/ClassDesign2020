package i.design.modules.user.models.put

import javax.validation.constraints.Email
import javax.validation.constraints.Min

data class RegisterModel(
    val name: String,
    val sex: String,
    @Email(message = "邮件格式错误")
    val email: String,
    @Min(16)
    val password: String
)
