package i.design.modules.user.services.impl

import com.github.openEdgn.logger4k.getLogger
import i.design.modules.user.models.table.GroupEntity
import i.design.modules.user.models.table.UserDetailsEntity
import i.design.modules.user.models.table.UserEntity
import i.design.modules.user.repository.GroupRepository
import i.design.modules.user.repository.UserRepository
import i.design.modules.user.services.ILoginService
import i.design.modules.user.services.IUserService
import i.design.utils.LocalDateTimeUtils
import i.design.utils.SnowFlake
import org.springframework.boot.ApplicationArguments
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.annotation.Resource

@Component
@Order(1)
@Service
class UserServiceImpl : IUserService {

    private val logger = getLogger()

    @Resource
    private lateinit var userRepository: UserRepository

    @Resource
    private lateinit var loginService: ILoginService

    @Resource
    private lateinit var groupRepository: GroupRepository


    @Resource
    private lateinit var snowFlake: SnowFlake

    @Resource
    private lateinit var localDateTimeUtils: LocalDateTimeUtils


    override fun addUser(
        email: String,
        password: String,
        group: GroupEntity,
        expired: Boolean
    ): UserEntity {
        val userEntity = UserEntity(
            snowFlake.nextId(), email, password,
            1,
            LocalDateTime.now(),
            localDateTimeUtils.MIN,
            if (expired) LocalDateTime.now() else localDateTimeUtils.MAX,
            group,
            UserDetailsEntity(snowFlake.nextId())
        )
        return userRepository.save(userEntity)
    }


    override fun run(args: ApplicationArguments?) {
        // 判断是否存在管理员账户
        if (groupRepository.findByAdminGroup(true).isEmpty()) {
            val groupEntity = GroupEntity(0, true, "ADMIN", "自动建立的管理员账户")
            groupRepository.save(groupEntity)
        }

        var notAdmin = true
        val groups = groupRepository.findByAdminGroup(true)
        groups.forEach {
            if (it.userList.isNotEmpty()) {
                notAdmin = false
                return@forEach
            }
        }
        if (notAdmin) {
            logger.warn("系统中无管理员账户，将自动添加！")
            val email = "admin@admin.com"
            val plainPassword = "admin"
            addUser(
                email, loginService.createPassword(plainPassword),
                groups.first(), true
            )
            logger.warn("添加完成！ 账户 ：{}, 密码:{}. 请立即更改", email, plainPassword)

        }

    }

}