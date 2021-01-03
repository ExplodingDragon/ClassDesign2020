package i.design.modules.question.repositories

import i.design.modules.question.models.entities.QuestionItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionItemRepository : JpaRepository<QuestionItemEntity, Long> {
}