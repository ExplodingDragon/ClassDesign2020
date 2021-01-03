package i.design.modules.question.services.impl

import i.design.handlers.exceptions.ApplicationExceptions
import i.design.modules.question.models.output.QuestionInfoStatusModel
import i.design.modules.question.models.output.QuestionStatusModel
import i.design.modules.question.repositories.QuestionRepository
import i.design.modules.question.services.IAskQuestionService
import i.design.modules.question.services.IQuestionService
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class AskQuestionServiceImpl : IAskQuestionService {
    @Resource
    private lateinit var questionService: IQuestionService

    @Resource
    private lateinit var questionRepository: QuestionRepository
    override fun questionStatus(id: Long): QuestionInfoStatusModel {
        val findById = questionRepository.findById(id)
        return if (findById.isEmpty) {
            QuestionInfoStatusModel(id = id, guest = false)
        } else {
            QuestionInfoStatusModel(id = id, guest = findById.get().allowGuest)
        }
    }

    override fun questionDetails(id: Long): QuestionStatusModel {
        val findById = questionRepository.findById(id)
        return if (findById.isEmpty) {
            throw ApplicationExceptions.forbidden("没有权限访问！")
        } else {
            val selectOne = questionService.selectOne(id)
            if (!selectOne.allowGuest) {
                throw ApplicationExceptions.forbidden("没有权限访问！")
            }
            selectOne
        }
    }
}