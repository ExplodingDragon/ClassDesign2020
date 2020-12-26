package i.design.modules.auth.controllers

import i.design.modules.auth.models.`in`.LoginModel
import i.design.modules.auth.services.IAuthService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource
import javax.validation.Valid

@RestController
@RequestMapping("/auth/")
class AuthController {
    @Resource(name = "auth")
    private lateinit var authService: IAuthService

    @Operation(summary = "登录账户")
    @PostMapping("login", produces = ["application/json"], consumes = ["application/json"])
    fun login(@Valid @RequestBody model: LoginModel) = authService.login(model)
}