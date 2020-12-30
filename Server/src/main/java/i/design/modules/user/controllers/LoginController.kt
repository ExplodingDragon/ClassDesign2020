package i.design.modules.user.controllers

import i.design.modules.user.models.put.LoginModel
import i.design.modules.user.models.put.RegisterModel
import i.design.modules.user.services.ILoginService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class LoginController {
    @Resource(name = "auth")
    private lateinit var loginService: ILoginService

    @Operation(summary = "密码传输算法", description = "密码传输算法")
    @GetMapping("", produces = ["application/json"])
    fun passwordAlgorithm() = loginService.passwordAlgorithm

    @Operation(summary = "登录账户", description = "输入邮箱和密码，登录账户")
    @PostMapping("/login", produces = ["application/json"], consumes = ["application/json"])
    fun login(@Valid @RequestBody model: LoginModel) = loginService.login(model)

    @Operation(summary = "注册")
    @PostMapping("/register", produces = ["application/json"], consumes = ["application/json"])
    fun register(@Valid @RequestBody registerModel: RegisterModel) = loginService.register(registerModel)

}