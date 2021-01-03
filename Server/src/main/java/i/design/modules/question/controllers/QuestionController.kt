package i.design.modules.question.controllers

import i.design.handlers.jwt.UserId
import i.design.modules.question.models.input.QuestionCreateModel
import i.design.modules.question.models.input.QuestionUpdateModel
import i.design.modules.question.services.IQuestionService
import i.design.modules.token.models.annotations.Token
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

// questions
@Tag(name = "问卷", description = "USER API")
@RestController
@RequestMapping("/api/questions")
class QuestionController {
    @Resource
    private lateinit var questionService: IQuestionService

    @Operation(
        summary = "得到问卷信息", tags = ["question"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(false)
    @GetMapping("")
    fun selectAll(
        @Param("index") index: Int,
        @Param("size") size: Int,
        @UserId userId: Long
    ) = questionService.selectAll(index, size, userId)

    @Operation(
        summary = "根据问卷ID得到问卷信息", tags = ["question"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(false)
    @GetMapping("{id}", produces = ["application/json"])
    fun selectOne(@PathVariable id: Long, @UserId userId: Long) =
        questionService.selectOne(id, userId)

    @Operation(
        summary = "插入新的问卷", tags = ["question"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(false)
    @PostMapping("", consumes = ["application/json"], produces = ["application/json"])
    fun insert(@RequestBody data: QuestionCreateModel, @UserId userId: Long) =
        questionService.insert(data, userId)

    @Operation(
        summary = "更新问卷信息", tags = ["question"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(false)
    @PutMapping("{id}", consumes = ["application/json"], produces = ["application/json"])
    fun update(
        @PathVariable id: Long,
        @RequestBody data: QuestionUpdateModel,
        @UserId userId: Long,
    ) = questionService.update(id, data, userId)

    @Operation(
        summary = "删除问卷", tags = ["question"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(false)
    @DeleteMapping("{id}",produces = ["application/json"])
    fun delete(@PathVariable id: Long, @UserId userId: Long) =
        questionService.delete(id, userId)

    @Operation(
        summary = "查询问卷数目", tags = ["question"], security = [
            SecurityRequirement(name = "auth")
        ]
    )
    @Token(false)
    @GetMapping("count",produces = ["application/json"])
    fun length(@UserId userId: Long) = questionService.length(userId)

}

