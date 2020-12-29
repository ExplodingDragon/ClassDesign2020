package i.design.modules.auth.models.put

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Email


@Schema(title = "用户登录")
data class LoginModel(
    @Schema(title = "邮箱")
    @get:Email(message = "非邮件字段")
    var email: String,
    @Schema(title = "密码")
    var password: String
)
