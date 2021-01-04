package i.design.modules.question.repositories

import i.design.modules.question.models.entities.QuestionAskEntity
import i.design.modules.question.models.entities.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AnswerRepository : JpaRepository<QuestionAskEntity, Long> {
}