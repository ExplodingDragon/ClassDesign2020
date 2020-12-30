package i.design.modules.user.repository

import i.design.modules.user.models.table.UserEntity
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.validation.constraints.Email

@Repository
interface UserRepository : CrudRepository<UserEntity, Long>, QuerydslPredicateExecutor<UserEntity> {

    fun findByEmail(@Email email: String): List<UserEntity>
}