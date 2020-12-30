package i.design.modules.user.services.impl

import com.github.openEdgn.logger4k.getLogger
import com.github.open_edgn.security4k.hash.sha256Sum
import com.github.open_edgn.security4k.hash.sha384Sum
import i.design.handlers.exceptions.ApplicationExceptions
import i.design.modules.token.services.ITokenService
import i.design.modules.user.models.out.AlgorithmStatusModel
import i.design.modules.user.models.out.LoginStatusModel
import i.design.modules.user.models.out.RegisterStatusModel
import i.design.modules.user.models.put.LoginModel
import i.design.modules.user.models.put.RegisterModel
import i.design.modules.user.repository.UserRepository
import i.design.modules.user.services.ILoginService
import i.design.modules.user.services.IUserService
import org.springframework.boot.ApplicationArguments
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.annotation.Resource

@Service("auth")
class LoginServiceImpl : ILoginService {
    private val logger = getLogger()

    override fun run(args: ApplicationArguments) {

    }

    override val passwordAlgorithm by lazy {
        AlgorithmStatusModel(
            "snap-{password}-asxasfazdvlasjnx",
            "SHA256(slat(password))"
        )
    }

    @Resource
    private lateinit var userService: IUserService

    @Resource
    private lateinit var userRepository: UserRepository

    @Resource
    private lateinit var tokenService: ITokenService<*>

    override fun register(registerModel: RegisterModel): RegisterStatusModel {
        val user = userService.addUser(
            registerModel.email,
            registerModel.password.sha384Sum(), mutableListOf()
        )
        return RegisterStatusModel(true, user.userDetail.name)
    }


    override fun login(model: LoginModel): LoginStatusModel {
        val users = userRepository.findByEmail(model.email)
        if (users.isEmpty()) {
            throw ApplicationExceptions.badRequest("用户名或密码错误！")
        }
        val user = users.first()
        if (model.password.sha384Sum() == user.password) {
            return LoginStatusModel(
                user.userDetail.name, tokenService.createToken(user),
                user.expired.isAfter(LocalDateTime.now())
            )
        } else {
            throw ApplicationExceptions.badRequest("用户名或密码错误！")
        }
    }

    override fun createPassword(plainPassword: String): String {
        return passwordAlgorithm.salt.replace("{password}", plainPassword).sha256Sum()
    }

}