package i.design.modules.question.services.impl

import com.github.openEdgn.logger4k.getLogger
import i.design.handlers.exceptions.ApplicationExceptions
import i.design.modules.question.models.entities.QuestionAskEntity
import i.design.modules.question.models.entities.QuestionAskItemEntity
import i.design.modules.question.models.entities.QuestionEntity
import i.design.modules.question.models.input.AnswerModel
import i.design.modules.question.models.output.*
import i.design.modules.question.repositories.AnswerOptionRepository
import i.design.modules.question.repositories.AnswerRepository
import i.design.modules.question.repositories.QuestionRepository
import i.design.modules.question.services.IAskQuestionService
import i.design.modules.question.services.IQuestionService
import i.design.modules.users.repositories.UserRepository
import i.design.modules.users.services.IUserService
import i.design.utils.LocalDateTimeUtils
import i.design.utils.rest.RestStatus
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.Resource

@Service
class AskQuestionServiceImpl : IAskQuestionService {
    @Resource
    private lateinit var questionService: IQuestionService

    @Resource
    private lateinit var answerRepository: AnswerRepository

    @Resource
    private lateinit var questionRepository: QuestionRepository
    override fun questionStatus(id: Long): QuestionInfoStatusModel {
        val findById = questionRepository.findById(id)
        return if (findById.isEmpty) {
            QuestionInfoStatusModel(id = id, guest = false)
        } else {
            QuestionInfoStatusModel(id = id, guest = findById.get().allowGuest)
        }
    }

    override fun questionDetails(id: Long): QuestionStatusModel {
        val findById = questionRepository.findById(id)
        return if (findById.isEmpty) {
            throw ApplicationExceptions.forbidden("没有权限访问！")
        } else {
            val selectOne = questionService.selectOne(id)
            if (!selectOne.allowGuest) {
                throw ApplicationExceptions.forbidden("没有权限访问！")
            }
            selectOne
        }
    }

    override fun postData(ans: AnswerModel, userId: Long): RestStatus<Long> {
        return internalPutData(ans)
    }

    override fun postData(ans: AnswerModel): RestStatus<Long> {
        val findById = questionRepository.findById(ans.questionId)
        if (findById.isEmpty) {
            throw ApplicationExceptions.badRequest()
        }
        val get = findById.get()
        if (get.allowGuest.not()) {
            throw ApplicationExceptions.forbidden("")
        }
        return internalPutData(ans)
    }

    private fun internalPutData(ans: AnswerModel): RestStatus<Long> {
        val findById = questionRepository.findById(ans.questionId)
        if (findById.isEmpty) {
            throw ApplicationExceptions.badRequest()
        }
        val get = findById.get()
        val toMap = get.content.map { Pair(it.id, it) }.toMap()
        val mutableList = mutableListOf<QuestionAskItemEntity>()
        ans.answers.forEach { it1 ->
            it1.data.forEach { it2 ->
                mutableList.add(
                    QuestionAskItemEntity(
                        id = 0, content = it2, questItem = toMap[it1.id]!!
                    )
                )
            }
        }
        val id = answerRepository.save(
            QuestionAskEntity(
                id = 0, question = get, content = mutableList
            )
        ).id
        return RestStatus(true, id)
    }

    @Resource
    private lateinit var userRepository: UserRepository
    override fun info(userId: Long): UserAskInfo {

        val user = userRepository.findById(userId).get()
        var askYourQuestSize = 0L
        for (questionEntity in questionRepository.findAllByCreateUser(user)) {
            askYourQuestSize += questionEntity.askItem.size
        }
        return UserAskInfo(
            LocalDateTimeUtils.format(user.registerDate),
            createSize = questionRepository.countByCreateUserId(userId),
            askYourQuestSize = askYourQuestSize
        )
    }

    @Resource
    private lateinit var userService: IUserService

    @Resource
    private lateinit var answerOptionRepository: AnswerOptionRepository
    override fun details(id: Long, userId: Long): Question2AskStatusModel {
        val data = if (userService.isAdmin(userId)) {
            questionRepository.findById(id)
        } else {
            questionRepository.findTopByCreateUserIdAndId(userId, id)
        }
        val internalSelect = internalSelect(data.get())
        for (content in internalSelect.contents) {
            for (option in content.options) {
                option.selectSize = answerOptionRepository.countAllByContentLike(option.id.toString())
            }
        }
        return internalSelect
    }

    private fun internalSelect(entity: QuestionEntity): Question2AskStatusModel {
        val contents = LinkedList<Question2ASKItemStatusModel>()
        for (questionItemEntity in entity.content) {
            contents.add(
                Question2ASKItemStatusModel(
                    id = questionItemEntity.id,
                    optionType = questionItemEntity.type,
                    title = questionItemEntity.content, options =
                    questionItemEntity.option.map {
                        Question2ASKItemOptionStatusModel(
                            id = it.id, content = it.content
                        )
                    }.toList()
                )
            )
        }
        return Question2AskStatusModel(
            id = entity.id,
            createUser = entity.createUser.userDetail.name,
            contents = contents,
            askSize = entity.askItem.size.toLong(),
            title = entity.title,
            allowGuest = entity.allowGuest
        )
    }
}