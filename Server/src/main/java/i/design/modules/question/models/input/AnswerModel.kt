package i.design.modules.question.models.input

data class AnswerModel(
    val questionId: Long,
    val answers: List<AnswerOptionModel>
)

data class AnswerOptionModel(
    val id: Long,
    val data: List<String>
)
