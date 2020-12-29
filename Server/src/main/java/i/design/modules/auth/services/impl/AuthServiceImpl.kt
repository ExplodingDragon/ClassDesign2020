package i.design.modules.auth.services.impl

import com.github.openEdgn.logger4k.getLogger
import i.design.modules.auth.models.out.LoginStatusModel
import i.design.modules.auth.models.put.LoginModel
import i.design.modules.auth.services.IAuthService
import i.design.modules.token.utils.TokenUtils
import i.design.modules.user.entities.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("auth")
class AuthServiceImpl : IAuthService {
    private val logger = getLogger()

    @Autowired
    private lateinit var tokenUtils: TokenUtils

    override fun login(model: LoginModel): LoginStatusModel {
        val entity = UserEntity(0, "12", "qwq@qq.cc", "zxcv123456", "男", 1)
        return LoginStatusModel("Hello World", tokenUtils.create(entity))
    }


}