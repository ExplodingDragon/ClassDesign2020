package i.design.modules.question.models.input

import i.design.modules.question.models.entities.QuestionOption

data class QuestionCreateModel(
    val title: String,
    val allowGuest:Boolean,
    val content: List<QuestionCreateItemModel>
)

data class QuestionCreateItemModel(
    val title: String,
    val optionType: QuestionOption,
    val content: List<QuestionCreateItemOptionModel>
)

data class QuestionCreateItemOptionModel(
    val content: String
)