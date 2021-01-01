package i.design.modules.users.services.impl

import com.github.openEdgn.logger4k.getLogger
import com.github.open_edgn.security4k.hash.sha384Sum
import i.design.handlers.exceptions.ApplicationExceptions
import i.design.modules.users.models.entities.UserDetailsEntity
import i.design.modules.users.models.entities.UserEntity
import i.design.modules.users.models.rest.UserModel
import i.design.modules.users.repositories.UserRepository
import i.design.modules.users.services.IUserService
import i.design.utils.SnowFlake
import i.design.utils.rest.RestStatus
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.annotation.Resource

@Service
class UserServiceImpl : IUserService {
    @Resource
    private lateinit var snowFlake: SnowFlake

    private val logger = getLogger()

    @Resource
    private lateinit var userRepository: UserRepository
    override fun selectAll(index: Int, length: Int): List<UserModel> {
        val toList = userRepository.findAll(PageRequest.of(index, length)).map {
            UserModel(
                it.id, it.email, it.userDetail.sex, it.userDetail.name, it.userDetail.imageUrl,
                admin = it.admin,
                canLogin = it.canLogin
            )
        }.toList()
        return toList
    }

    override fun selectOneById(id: Long): UserModel {
        val data = userRepository.findById(id)
        if (data.isEmpty) {
            throw ApplicationExceptions.badRequest("无id 为 $id 的用户。")
        }
        val get = data.get()
        val userDetail = get.userDetail
        return UserModel(
            get.id, email = get.email, sex = userDetail.sex,
            name = userDetail.name, image = userDetail.imageUrl,
            admin = get.admin, canLogin = get.canLogin
        )
    }

    override fun insert(user: UserModel): RestStatus<Long> {
        return UserEntity(
            id = snowFlake.nextId(),
            email = user.email,
            password = user.password.sha384Sum(),
            admin = user.admin,
            canLogin = user.canLogin,
            registerDate = LocalDateTime.now(),
            lastLoginDate = LocalDateTime.now(),
            userDetail = UserDetailsEntity()
        ).run { RestStatus(true, userRepository.save(this).id) }
    }

    override fun update(id: Long, user: UserModel): RestStatus<Long> {
        return UserEntity(
            id = user.id,
            email = user.email,
            password = user.password.sha384Sum(),
            admin = false,
            canLogin = false,
            registerDate = LocalDateTime.now(),
            lastLoginDate = LocalDateTime.now(),
            userDetail = UserDetailsEntity()
        ).run { RestStatus(true, userRepository.save(this).id) }
    }

    override fun delete(id: Long): RestStatus<Long> {
        userRepository.deleteById(id)
        return RestStatus(true, id)
    }
}