package i.design.modules.user.repository

import i.design.modules.user.models.table.GroupEntity
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository : CrudRepository<GroupEntity, Long>, QuerydslPredicateExecutor<GroupEntity> {
    fun findByAdminGroup(isAdmin: Boolean): List<GroupEntity>


}