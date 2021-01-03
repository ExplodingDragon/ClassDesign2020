package i.design.modules.question.services

import i.design.modules.question.models.output.QuestionInfoStatusModel
import i.design.modules.question.models.output.QuestionStatusModel

interface IAskQuestionService {
    fun questionStatus(id: Long): QuestionInfoStatusModel
    fun questionDetails(id: Long): QuestionStatusModel

}
