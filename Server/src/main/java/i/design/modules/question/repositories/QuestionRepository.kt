package i.design.modules.question.repositories

import i.design.modules.question.models.entities.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface QuestionRepository : JpaRepository<QuestionEntity, Long> {
    fun findAllByCreateUserId(userId: Long): List<QuestionEntity>
    fun findTopByCreateUserIdAndId(userId: Long, id: Long): Optional<QuestionEntity>

    fun countByCreateUserId(userId: Long): Long
}