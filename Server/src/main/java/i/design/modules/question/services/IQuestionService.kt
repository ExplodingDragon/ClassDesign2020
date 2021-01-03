package i.design.modules.question.services

import i.design.modules.question.models.input.QuestionCreateModel
import i.design.modules.question.models.input.QuestionUpdateModel
import i.design.modules.question.models.output.QuestionStatusModel
import i.design.utils.rest.RestStatus

interface IQuestionService {

    fun selectAll(index: Int, size: Int, userId: Long): List<QuestionStatusModel>

    fun insert(data: QuestionCreateModel, userId: Long): RestStatus<Long>

    fun delete(id: Long, userId: Long): RestStatus<Long>

    fun update(id: Long, data: QuestionUpdateModel, userId: Long): RestStatus<Long>

    fun selectOne(id: Long, userId: Long): QuestionStatusModel

    fun selectOne(id: Long): QuestionStatusModel

    fun length(userId: Long): RestStatus<Long>

}
