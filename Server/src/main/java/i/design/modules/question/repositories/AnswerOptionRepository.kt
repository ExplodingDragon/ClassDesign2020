package i.design.modules.question.repositories

import i.design.modules.question.models.entities.QuestionAskItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerOptionRepository :JpaRepository<QuestionAskItemEntity,Long> {
    fun countAllByQuestItemId(id:Long):Long
    fun countAllByContentLike(content: String):Long
}