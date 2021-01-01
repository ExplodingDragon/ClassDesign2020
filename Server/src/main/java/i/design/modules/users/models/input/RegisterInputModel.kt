package i.design.modules.users.models.input

import javax.validation.constraints.Email

/**
 * 注册信息
 * @property email String 邮箱
 * @property password String 密码
 * @constructor
 */
data class RegisterInputModel(
    @Email
    val email: String,
    val password: String
)