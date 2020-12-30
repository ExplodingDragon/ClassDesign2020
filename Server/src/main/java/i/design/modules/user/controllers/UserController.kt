package i.design.modules.user.controllers

import i.design.handlers.jwt.UserId
import i.design.modules.user.models.TokenInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController {


    @TokenInfo
    @GetMapping("/data")
    fun getData(@UserId id: Long) = "hello , $id"
}