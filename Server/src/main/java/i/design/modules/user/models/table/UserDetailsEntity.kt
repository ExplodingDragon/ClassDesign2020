package i.design.modules.user.models.table

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


/**
 *
 * 用户详情信息
 */
@Entity(name = "t_user_details")
data class UserDetailsEntity(
    @Id
    val id: Long,
    @Column(name = "name", nullable = false, length = 50)
    var name: String = "",
    @Column(name = "sex", nullable = false, length = 1000)
    var sex: String = ""
)