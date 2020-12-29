package i.design.modules.auth.services

import i.design.modules.auth.models.put.LoginModel
import i.design.modules.auth.models.out.LoginStatusModel

interface IAuthService {
    fun login(model: LoginModel): LoginStatusModel
}
