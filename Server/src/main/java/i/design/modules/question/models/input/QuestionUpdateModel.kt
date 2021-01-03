package i.design.modules.question.models.input

import i.design.modules.question.models.entities.QuestionOption

data class QuestionUpdateModel(
    val id: Long,
    val title: String,
    val allowGuest:Boolean,
    val contents: MutableList<QuestionUpdateItemModel>
)

data class QuestionUpdateItemModel(
    val id: Long,
    val optionType: QuestionOption,
    val title: String,
    val options: MutableList<QuestionUpdateItemOptionStatusModel>
)

data class QuestionUpdateItemOptionStatusModel(
    val id: Long,
    val content: String,
)
