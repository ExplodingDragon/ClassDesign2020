package i.design.modules.users.controllers

import i.design.modules.token.models.annotations.Token
import i.design.modules.users.models.rest.UserModel
import i.design.modules.users.services.IUserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@Tag(name = "用户接口", description = "USER API")

@RestController
@RequestMapping("/api/users")
class RestUserController {
    @Resource
    private lateinit var userService: IUserService

    @Operation(
        summary = "得到全部用户信息", tags = ["user"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(true)
    @GetMapping("", produces = ["application/json"])
    fun getAllUsers(
        @Param("index") pageIndex: Int,
        @Param("size") pageSize: Int
    ) = userService.selectAll(pageIndex, pageSize)

    @Operation(
        summary = "得到单个用户信息", tags = ["user"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(true)
    @GetMapping("{id}", produces = ["application/json"])
    fun getOneUserById(@PathVariable id: Long) = userService.selectOneById(id)

    @Operation(
        summary = "新增用户", tags = ["user"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(true)
    @PostMapping("", consumes = ["application/json"], produces = ["application/json"])
    fun insertUser(@RequestBody user: UserModel) = userService.insert(user)

    @Operation(
        summary = "更新用户信息", tags = ["user"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(true)
    @PutMapping("{id}", consumes = ["application/json"], produces = ["application/json"])
    fun updateUser(@RequestBody user: UserModel, @PathVariable id: Long) = userService.update(id, user)

    @Operation(
        summary = "删除用户", tags = ["user"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(true)
    @DeleteMapping("{id}", produces = ["application/json"])
    fun deleteUser(@PathVariable id: Long) = userService.delete(id)
    @Operation(
        summary = "查询用户数目", tags = ["user"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(true)
    @GetMapping("length", produces = ["application/json"])
    fun length() = userService.length()

}
