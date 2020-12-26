package i.design.modules.auth.services.impl

import com.github.openEdgn.logger4k.getLogger
import i.design.modules.auth.models.`in`.LoginModel
import i.design.modules.auth.models.out.LoginStatusModel
import i.design.modules.auth.services.IAuthService
import org.springframework.stereotype.Service

@Service("auth")
class AuthServiceImpl :IAuthService {
    private val logger = getLogger()
    override fun login(model: LoginModel): LoginStatusModel {
        logger.info("email :{},password:{}",model.email,model.password)
        return LoginStatusModel("Hello World")
    }
}