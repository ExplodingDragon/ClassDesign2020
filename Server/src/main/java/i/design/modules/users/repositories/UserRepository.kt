package i.design.modules.users.repositories

import i.design.modules.users.models.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {


    fun existsByEmail(email: String): Boolean
    fun findOneByEmail(email: String):Optional<UserEntity>
    fun existsByAdmin(b: Boolean):Boolean
}