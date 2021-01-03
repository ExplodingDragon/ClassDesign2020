package i.design.modules.question.models.output

import i.design.modules.question.models.entities.QuestionOption

data class QuestionStatusModel(
    val id: Long,
    val title: String,
    val createUser: String,
    val allowGuest:Boolean,
    val askSize: Long,
    val contents: MutableList<QuestionItemStatusModel>
)

data class QuestionItemStatusModel(
    val id: Long,
    val optionType: QuestionOption,
    val title: String,
    val options: List<QuestionItemOptionStatusModel>
)

data class QuestionItemOptionStatusModel(
    val id: Long,
    val content: String,
)
