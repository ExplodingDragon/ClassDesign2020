package i.design.modules.users.services.impl

import com.github.open_edgn.security4k.hash.sha384Sum
import i.design.handlers.exceptions.ApplicationExceptions
import i.design.modules.token.services.ITokenService
import i.design.modules.users.models.input.LoginInputModel
import i.design.modules.users.models.input.RegisterInputModel
import i.design.modules.users.models.output.LoginStatusModel
import i.design.modules.users.models.output.LogoutStatusModel
import i.design.modules.users.models.output.RegisterStatusModel
import i.design.modules.users.models.rest.UserModel
import i.design.modules.users.repositories.UserRepository
import i.design.modules.users.services.IAuthService
import i.design.modules.users.services.IUserService
import i.design.utils.SnowFlake
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.annotation.Resource

@Service("auth")
class AuthServiceImpl : IAuthService {
    @Resource
    private lateinit var userRepository: UserRepository

    @Resource
    private lateinit var userService: IUserService

    @Resource
    private lateinit var snowFlake: SnowFlake

    @Resource
    private lateinit var tokenService: ITokenService<*>

    override fun tryLogin(loginModel: LoginInputModel): LoginStatusModel {
        val email = loginModel.email.toLowerCase()
        val encodedPasswd = loginModel.password.sha384Sum()
        val findOneByEmail = userRepository.findOneByEmail(email)
        if (findOneByEmail.isEmpty) {
            throw ApplicationExceptions.badRequest("用户名或密码错误！")
        } else {
            findOneByEmail.get().run {
                if (encodedPasswd == password) {
                    if (!canLogin) {
                        throw ApplicationExceptions.forbidden("此账户已被禁用！")
                    }
                    lastLoginDate = LocalDateTime.now()
                    userRepository.save(this)

                    return LoginStatusModel(
                        name = userDetail.name,
                        image = userDetail.imageUrl,
                        token = tokenService.createTokenByUserId(id), email = email[0] + "****" +
                                email.substring(email.lastIndexOf("@")),
                        admin = admin
                    )
                } else {
                    throw ApplicationExceptions.badRequest("用户名或密码错误！")
                }
            }
        }
    }

    override fun register(registerInfo: RegisterInputModel): RegisterStatusModel {
        val email = registerInfo.email
        if (userRepository.existsByEmail(email.toLowerCase()).not()) {
            // 可注册
            registerInfo.run {
                userService.insert(
                    UserModel(
                        id = 0,
                        email = email,
                        sex = "",
                        name = "",
                        image = "",
                        password = password,
                        admin = false,
                        canLogin = true
                    )
                )
            }
            return RegisterStatusModel(status = true, message = "注册成功！")
        } else {
            throw ApplicationExceptions.badRequest("该邮箱已被注册！")
        }
    }

    override fun logout(userId: Long): LogoutStatusModel {
        tokenService.clearTokenById(userId)
        return LogoutStatusModel("已注销")

    }
}