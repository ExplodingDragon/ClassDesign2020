package i.design.modules.question.services

import i.design.modules.question.models.input.AnswerModel
import i.design.modules.question.models.output.Question2AskStatusModel
import i.design.modules.question.models.output.QuestionInfoStatusModel
import i.design.modules.question.models.output.QuestionStatusModel
import i.design.modules.question.models.output.UserAskInfo
import i.design.utils.rest.RestStatus

interface IAskQuestionService {
    fun questionStatus(id: Long): QuestionInfoStatusModel
    fun questionDetails(id: Long): QuestionStatusModel
    fun postData(ans: AnswerModel, userId: Long): RestStatus<Long>
    fun postData(ans: AnswerModel): RestStatus<Long>
    fun info(userId: Long): UserAskInfo
    fun details(id: Long, userId: Long): Question2AskStatusModel

}
