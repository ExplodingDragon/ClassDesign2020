package i.design.modules.users.models.entities

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "t_user_details")
class UserDetailsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String = "Register User",
    var sex: String = "男",
    var birthday: LocalDateTime = LocalDateTime.now(),
    var imageUrl: String = ""
)