package i.design.modules.users.models.entities

import java.time.LocalDateTime
import javax.persistence.*
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
    @Column(nullable = false)
    var canLogin: Boolean,
    @Column(nullable = false)
    var registerDate: LocalDateTime,
    @Column(nullable = false)
    var lastLoginDate: LocalDateTime,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_detail")
    var userDetail: UserDetailsEntity
)