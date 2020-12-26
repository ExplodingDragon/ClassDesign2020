package i.design.modules.user.entities

import javax.persistence.*
import javax.validation.constraints.Size


@Entity(name = "t_auth")
data class AuthEntity(
    @Id
    @GeneratedValue
    val id: Long,
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 20)
    var name: String,
    @Column(nullable = false, length = 200)
    var note: String = "",
    @ManyToMany(mappedBy = "authList",fetch = FetchType.EAGER)
    val groupList: List<GroupEntity>
)
