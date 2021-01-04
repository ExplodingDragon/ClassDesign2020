package i.design.modules.question.controllers

import i.design.modules.question.models.input.AnswerModel
import i.design.modules.question.services.IAskQuestionService
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@RestController
@RequestMapping("/pages/questing")
class AskQuestionController {
    @Resource
    private lateinit var askQuestionService: IAskQuestionService

    @PostMapping("/ask", consumes = ["application/json"], produces = ["application/json"])
    fun postPrivateAnswer(@RequestBody ans: AnswerModel) = askQuestionService.postData(ans)
//    , consumes = ["application/json"], produces = ["application/json"]

    @GetMapping("/{id}", produces = ["application/json"])
    fun questStatus(@PathVariable id: Long) = askQuestionService.questionStatus(id)

    @GetMapping("/{id}/details", produces = ["application/json"])
    fun questDetails(@PathVariable id: Long) = askQuestionService.questionDetails(id)

}