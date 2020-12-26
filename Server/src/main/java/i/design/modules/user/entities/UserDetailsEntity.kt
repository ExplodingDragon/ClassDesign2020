package i.design.modules.user.entities

import java.time.LocalDateTime
import javax.persistence.*


/**
 *
 * 用户详情信息
 */
@Entity(name = "t_user_details")
data class UserDetailsEntity(
    @Id
    @GeneratedValue
    val id: Long,
    @Column(name = "details_key", nullable = false, length = 50)
    var key: String,
    @Column(name = "details_value", nullable = false, length = 1000)
    var value: String,
    @Column(nullable = false)
    var createTime: LocalDateTime,
    @ManyToOne(cascade = [CascadeType.MERGE, CascadeType.REFRESH], optional = false)
    @JoinColumn(name = "user_id")
    var user: UserEntity
)