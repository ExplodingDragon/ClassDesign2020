package i.design.modules.auth.models.`in`

import javax.validation.constraints.Email
import javax.validation.constraints.Size


data class LoginModel(
    @get:Size(min = 1, max = 100)
    @get:Email(message = "非邮件字段")
    var email: String,
    var password: String
)
