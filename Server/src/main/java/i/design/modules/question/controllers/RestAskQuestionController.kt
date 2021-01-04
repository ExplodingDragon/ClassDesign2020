package i.design.modules.question.controllers

import i.design.handlers.jwt.UserId
import i.design.modules.question.models.input.AnswerModel
import i.design.modules.question.services.IAskQuestionService
import i.design.modules.token.models.annotations.Token
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@RestController
@RequestMapping("/api/ask")
class RestAskQuestionController {
    @Resource
    private lateinit var askQuestionService: IAskQuestionService

    @Token(false)
    @PostMapping("", consumes = ["application/json"], produces = ["application/json"])
    fun postPrivateAnswer(@RequestBody ans: AnswerModel, @UserId userId: Long) =
        askQuestionService.postData(ans, userId)

    @Token(false)
    @GetMapping("/info", produces = ["application/json"])
    fun askInfo(@UserId userId: Long) = askQuestionService.info(userId)


    @Token(false)
    @GetMapping("/{id}", produces = ["application/json"])
    fun details(@PathVariable id:Long, @UserId userId: Long) = askQuestionService.details(id,userId)
}