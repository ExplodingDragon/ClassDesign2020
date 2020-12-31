package i.design.modules.users.models.entities

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.Email

@Entity(name = "t_user")
data class UserEntity(
    @Id
    val id: Long,
    @Email
    @Column(nullable = false, length = 128)
    var email: String,
    @Column(nullable = false, length = 128)
    var password: String,
    @Column(nullable = false)
    var admin: Boolean,
    // reg
    @Column(nullable = false)
    var registerDate: LocalDateTime

)