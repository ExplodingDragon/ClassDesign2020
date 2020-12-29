package i.design.modules.user.controllers

import i.design.modules.auth.models.TokenInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController {


    @TokenInfo
    @GetMapping("/data")
    fun getData() = "asa"
}