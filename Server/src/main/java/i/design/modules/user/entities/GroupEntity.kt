package i.design.modules.user.entities

import javax.persistence.*


@Entity(name = "t_group")
data class GroupEntity(
    @Id
    @GeneratedValue var id: Long,
    @Column(nullable = false, length = 20)
    var groupName: String,
    @Column(nullable = false, length = 200)
    var note: String = "",
    @ManyToMany
    @JoinTable(
        name = "t_group_auth",
        joinColumns = [JoinColumn(name = "group_id")],
        inverseJoinColumns = [JoinColumn(name = "auth_id")]
    )
    val authList: MutableList<AuthEntity>,
    @ManyToMany(mappedBy = "groupList", fetch = FetchType.EAGER)
    val userList: MutableList<UserEntity>
)