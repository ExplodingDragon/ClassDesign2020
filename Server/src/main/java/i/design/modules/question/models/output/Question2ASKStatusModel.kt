package i.design.modules.question.models.output

import i.design.modules.question.models.entities.QuestionOption

data class Question2AskStatusModel(
    val id: Long,
    val title: String,
    val createUser: String,
    val allowGuest: Boolean,
    val askSize: Long,
    val contents: MutableList<Question2ASKItemStatusModel>
)

data class Question2ASKItemStatusModel(
    val id: Long,
    val optionType: QuestionOption,
    val title: String,
    val options: List<Question2ASKItemOptionStatusModel>
)

data class Question2ASKItemOptionStatusModel(
    val id: Long,
    var selectSize: Long = 0,
    val content: String
)
