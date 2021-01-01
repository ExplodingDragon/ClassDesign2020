package i.design.modules.users.services

import i.design.modules.users.models.rest.UserModel
import i.design.utils.rest.RestStatus

interface IUserService {
    fun selectAll(index: Int, length: Int): List<UserModel>
    fun selectOneById(id: Long): UserModel
    fun insert(user: UserModel): RestStatus<Long>
    fun update(id: Long, user: UserModel): RestStatus<Long>
    fun delete(id: Long): RestStatus<Long>
}
