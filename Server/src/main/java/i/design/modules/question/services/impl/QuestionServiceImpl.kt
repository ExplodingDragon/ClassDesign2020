@file:Suppress("DuplicatedCode")

package i.design.modules.question.services.impl

import i.design.handlers.exceptions.ApplicationExceptions
import i.design.handlers.jwt.UserId
import i.design.modules.question.models.entities.QuestionEntity
import i.design.modules.question.models.entities.QuestionItemEntity
import i.design.modules.question.models.entities.QuestionOption
import i.design.modules.question.models.entities.QuestionOptionEntity
import i.design.modules.question.models.input.QuestionCreateModel
import i.design.modules.question.models.input.QuestionUpdateModel
import i.design.modules.question.models.output.QuestionItemOptionStatusModel
import i.design.modules.question.models.output.QuestionItemStatusModel
import i.design.modules.question.models.output.QuestionStatusModel
import i.design.modules.question.repositories.QuestionItemRepository
import i.design.modules.question.repositories.QuestionRepository
import i.design.modules.question.services.IQuestionService
import i.design.modules.users.repositories.UserRepository
import i.design.modules.users.services.IUserService
import i.design.utils.rest.RestStatus
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.Resource

@Service
class QuestionServiceImpl : IQuestionService {

    @Resource
    private lateinit var userService: IUserService

    @Resource
    private lateinit var userRepository: UserRepository


    @Resource
    private lateinit var questionRepository: QuestionRepository

    @Resource
    private lateinit var questionItemRepository: QuestionItemRepository
    override fun selectAll(index: Int, size: Int, userId: Long): List<QuestionStatusModel> {
        val iterable = if (userService.isAdmin(userId)) {
            questionRepository.findAll(PageRequest.of(index, size))
        } else {
            questionRepository.findAllByCreateUserId(userId)
        }
        val linkedList = LinkedList<QuestionStatusModel>()
        for (entity in iterable) {
            linkedList.add(
                QuestionStatusModel(
                    id = entity.id,
                    createUser = entity.createUser.userDetail.name,
                    contents = arrayListOf(),
                    askSize = entity.askItem.size.toLong(),
                    title = entity.title,
                    allowGuest = entity.allowGuest
                )
            )
        }
        return linkedList
    }

    override fun insert(data: QuestionCreateModel, userId: Long): RestStatus<Long> {
        val content = LinkedList<QuestionItemEntity>()
        val save = QuestionEntity(
            id = 0, title = data.title, allowGuest = data.allowGuest, createUser =
            userRepository.findById(userId).get(), content = content
        )
        for (model in data.content) {
            val contentItem = LinkedList<QuestionOptionEntity>()
            content.add(
                QuestionItemEntity(
                    id = 0, content = model.title, type = model.optionType, option = contentItem
                )
            )
            if (model.optionType != QuestionOption.INPUT) {
                for (optionModel in model.content) {
                    contentItem.add(QuestionOptionEntity(id = 0, content = optionModel.content))
                }
            }
        }
        return RestStatus(true, questionRepository.save(save).id)
    }

    override fun delete(id: Long, @UserId userId: Long): RestStatus<Long> {
        return if (userService.isAdmin(userId)) {
            questionRepository.deleteById(id)
            RestStatus(true, userId)
        } else {
            val get = questionRepository.findById(id).get()
            if (get.createUser.id == userId) {
                questionRepository.deleteById(id)
                RestStatus(true, id)
            } else {
                throw ApplicationExceptions.forbidden("没有权限删除！")
            }
        }

    }

    override fun update(id: Long, data: QuestionUpdateModel, userId: Long): RestStatus<Long> {
        val admin = userService.isAdmin(userId)
        val findById = questionRepository.findById(id)
        if (findById.isEmpty) {
            throw ApplicationExceptions.badRequest("未找到此ID的问题。")
        }
        val old = findById.get()
        if (old.createUser.id != id && !admin) {
            throw ApplicationExceptions.forbidden("没有权限处理此问题！")
        }
        questionItemRepository.deleteAll(old.content)
        val content = LinkedList<QuestionItemEntity>()
        val save = QuestionEntity(
            id = id, title = data.title, allowGuest = data.allowGuest, createUser =
            old.createUser, content = content
        )
        for (model in data.contents) {
            val contentItem = LinkedList<QuestionOptionEntity>()
            content.add(
                QuestionItemEntity(
                    id = 0, content = model.title, type = model.optionType, option = contentItem
                )
            )
            if (model.optionType != QuestionOption.INPUT) {
                for (optionModel in model.options) {
                    contentItem.add(QuestionOptionEntity(id = 0, content = optionModel.content))
                }
            }
        }
        return RestStatus(true, questionRepository.save(save).id)
    }

    override fun selectOne(id: Long, userId: Long): QuestionStatusModel {
        val data = if (userService.isAdmin(userId)) {
            questionRepository.findById(id)
        } else {
            questionRepository.findTopByCreateUserIdAndId(userId, id)
        }
        return internalSelect(data.get())
    }

    private fun internalSelect(entity: QuestionEntity): QuestionStatusModel {
        val contents = LinkedList<QuestionItemStatusModel>()
        for (questionItemEntity in entity.content) {
            contents.add(
                QuestionItemStatusModel(
                    id = questionItemEntity.id,
                    optionType = questionItemEntity.type,
                    title = questionItemEntity.content, options =
                    questionItemEntity.option.map {
                        QuestionItemOptionStatusModel(
                            id = it.id, content = it.content
                        )
                    }.toList()
                )
            )
        }
        return QuestionStatusModel(
            id = entity.id,
            createUser = entity.createUser.userDetail.name,
            contents = contents,
            askSize = entity.askItem.size.toLong(),
            title = entity.title,
            allowGuest = entity.allowGuest
        )
    }

    override fun selectOne(id: Long): QuestionStatusModel {
        val findById = questionRepository.findById(id)
        return internalSelect(findById.get())

    }

    override fun length(userId: Long): RestStatus<Long> {
        return RestStatus(
            true,
            if (userService.isAdmin(userId)) {
                questionRepository.count()
            } else {
                questionRepository.countByCreateUserId(userId)
            }

        )
    }


}

