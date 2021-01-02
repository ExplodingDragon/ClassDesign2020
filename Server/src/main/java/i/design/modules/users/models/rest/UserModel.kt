package i.design.modules.users.models.rest

import i.design.utils.LocalDateTimeUtils
import java.time.LocalDateTime

data class UserModel(
    val id: Long,
    val email: String,
    val sex: String,
    val name: String,
    val image: String,
    val password: String = "",
    val admin: Boolean = false,
    val regiserDate:String = LocalDateTimeUtils.format(LocalDateTime.now()),
    val canLogin: Boolean
)
