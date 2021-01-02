package i.design.modules.users.services

import i.design.modules.users.models.input.LoginInputModel
import i.design.modules.users.models.input.RegisterInputModel
import i.design.modules.users.models.output.LoginStatusModel
import i.design.modules.users.models.output.LogoutStatusModel
import i.design.modules.users.models.output.RegisterStatusModel

/**
 * 数据校验 Service
 */
interface IAuthService {
    /**
     * 尝试登录账户，返回登录结果
     *
     * @param loginModel LoginInputModel
     * @return LoginStatusModel
     */
    fun tryLogin(loginModel: LoginInputModel): LoginStatusModel

    /**
     *
     * 注册账户
     *
     */
    fun register(registerInfo: RegisterInputModel): RegisterStatusModel

    /**
     *
     * 注销账户
     *
     */
    fun logout(userId: Long): LogoutStatusModel


}
