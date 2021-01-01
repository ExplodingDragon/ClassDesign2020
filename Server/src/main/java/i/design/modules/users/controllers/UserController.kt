package i.design.modules.users.controllers

import i.design.modules.users.models.input.LoginInputModel
import i.design.modules.users.models.input.RegisterInputModel
import i.design.modules.users.services.IAuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource
import javax.validation.Valid

@RestController
@RequestMapping("/pages/")
class UserController {
    @Resource
    private lateinit var authService: IAuthService

    @PostMapping(
        "login",
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun login(@RequestBody @Valid loginInfo: LoginInputModel) = authService.tryLogin(loginInfo)

    @PostMapping(
        "register",
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun register(@RequestBody @Valid registerInfo: RegisterInputModel) = authService.register(registerInfo)

}