package i.design.modules.user.services

import i.design.modules.user.models.table.GroupEntity
import i.design.modules.user.models.table.UserEntity
import org.springframework.boot.ApplicationRunner


interface IUserService : ApplicationRunner {

    fun addUser(email: String, password: String, group:GroupEntity, expired: Boolean =false): UserEntity
}