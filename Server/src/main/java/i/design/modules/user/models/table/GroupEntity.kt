package i.design.modules.user.models.table

import javax.persistence.*


@Entity(name = "t_group")
data class GroupEntity(
    @Id
    var id: Long,
    @Column(nullable = false, length = 20)
    var adminGroup: Boolean,
    @Column(nullable = false, length = 20)
    var groupName: String,
    @Column(nullable = false, length = 200)
    var note: String = "",
    @OneToMany(mappedBy = "groupList", fetch = FetchType.EAGER)
    val userList: MutableList<UserEntity> = mutableListOf()
)