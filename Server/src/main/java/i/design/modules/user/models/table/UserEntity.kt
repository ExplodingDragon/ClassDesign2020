package i.design.modules.user.models.table

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "t_user")
data class UserEntity(
    @Id
    val id: Long,
    @Column(nullable = false, length = 40)
    var email: String,
    @Column(nullable = false, length = 100)
    var password: String,
    /**
     * 密码算法版本
     */
    @Column(nullable = false)
    var passwordEncrypt: Int,
    @Column(nullable = false)
    val createTime: LocalDateTime,
    @Column(nullable = false)
    var lastLoginTime: LocalDateTime,
    /**
     * 密码过期时间 ，-1 为用不过期
     */
    @Column(nullable = false)
    var expired: LocalDateTime,
    @ManyToOne(cascade = [CascadeType.MERGE, CascadeType.REFRESH], optional = true)
    @JoinTable(
        name = "group_id"
    )
    val groupList: GroupEntity,
    @JoinColumn(name = "userDetail")
    @OneToOne(cascade = [CascadeType.ALL])
    var userDetail: UserDetailsEntity
)
