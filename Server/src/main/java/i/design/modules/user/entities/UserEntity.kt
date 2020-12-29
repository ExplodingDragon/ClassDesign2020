package i.design.modules.user.entities

import javax.persistence.*

@Entity(name = "t_user")
data class UserEntity(
    @Id
    @GeneratedValue
    val id: Long,
    /**
     * 用户的姓名
     */
    @Column(nullable = false, length = 20)
    var name: String,
    @Column(nullable = false, length = 40)
    var email: String,
    @Column(nullable = false, length = 100)
    var password: String,
    @Column(nullable = false)
    var sex: String,
    /**
     * 密码算法版本
     */
    @Column(nullable = false)
    var passwordEncrypt: Int,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "t_user_group",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "group_id")]
    )
    val groupList: MutableList<GroupEntity> = mutableListOf(),
@OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
val details: MutableList<UserDetailsEntity> = mutableListOf()
)
