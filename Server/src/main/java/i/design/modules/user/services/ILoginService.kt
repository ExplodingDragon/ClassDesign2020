package i.design.modules.user.services

import i.design.modules.user.models.out.AlgorithmStatusModel
import i.design.modules.user.models.out.LoginStatusModel
import i.design.modules.user.models.out.RegisterStatusModel
import i.design.modules.user.models.put.LoginModel
import i.design.modules.user.models.put.RegisterModel
import org.springframework.boot.ApplicationRunner

interface ILoginService : ApplicationRunner {
    val passwordAlgorithm: AlgorithmStatusModel
    fun login(model: LoginModel): LoginStatusModel
    fun register(registerModel: RegisterModel): RegisterStatusModel
    fun createPassword(plainPassword: String): String
}
