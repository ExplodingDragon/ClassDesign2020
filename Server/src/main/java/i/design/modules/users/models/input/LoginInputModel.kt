package i.design.modules.users.models.input

import javax.validation.constraints.Email

data class LoginInputModel(
    @Email(message = "非E-MAIL")
    val email: String,
    val password: String
)
