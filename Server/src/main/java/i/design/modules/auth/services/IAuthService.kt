package i.design.modules.auth.services

import i.design.modules.auth.models.`in`.LoginModel
import i.design.modules.auth.models.out.LoginStatusModel

interface IAuthService {
    fun login(model: LoginModel): LoginStatusModel
}
