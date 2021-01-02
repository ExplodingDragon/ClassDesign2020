package i.design.modules.users.controllers

import i.design.handlers.jwt.UserId
import i.design.modules.token.models.annotations.Token
import i.design.modules.users.models.input.LoginInputModel
import i.design.modules.users.models.input.RegisterInputModel
import i.design.modules.users.services.IAuthService
import org.springframework.web.bind.annotation.*
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

    @Token(false)
    @GetMapping(
        "logout",
        produces = ["application/json"]
    )
    fun logout(@UserId id: Long) = authService.logout(id)

}