package i.design.modules.question.controllers

import i.design.modules.question.services.IAskQuestionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("/pages/questing")
class AskQuestionController {
    @Resource
    private lateinit var askQuestionService: IAskQuestionService

//    , consumes = ["application/json"], produces = ["application/json"]

    @GetMapping("/{id}", produces = ["application/json"])
    fun questStatus(@PathVariable id: Long) = askQuestionService.questionStatus(id)

    @GetMapping("/{id}/details", produces = ["application/json"])
    fun questDetails(@PathVariable id: Long) = askQuestionService.questionDetails(id)

}