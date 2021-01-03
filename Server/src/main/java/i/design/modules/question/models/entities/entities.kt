package i.design.modules.question.models.entities

import i.design.modules.users.models.entities.UserEntity
import javax.persistence.*

/**
 *
 * 问题表
 */
@Entity(name = "t_question")
data class QuestionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var title: String,
    var allowGuest: Boolean,
    @OneToOne
    @JoinColumn(name = "createUserId")
    var createUser: UserEntity,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "quest_item_id")
    val content: MutableList<QuestionItemEntity> = mutableListOf(),
    @OneToMany(cascade = [CascadeType.ALL],fetch = FetchType.EAGER)
    @JoinColumn(name = "quest_ask_id")
    val askItem: MutableList<QuestionAskEntity> = mutableListOf()
)

/**
 * 问题详情表
 *
 * @property id Long
 * @property content String
 * @property type QuestionOption
 * @property option MutableList<QuestionOptionEntity>
 * @constructor
 */
@Entity(name = "t_quest_item")
data class QuestionItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var content: String,
    var type: QuestionOption,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "quest_item_option_id")
    val option: MutableList<QuestionOptionEntity> = mutableListOf()
)

/**
 * 问题选项表
 * @property id Long
 * @property content String
 * @constructor
 */
@Entity(name = "t_quest_item_option")
data class QuestionOptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val content: String
)

/**
 * 问题类型
 */
enum class QuestionOption {
    ONE_SELECT, MANY_SELECT, INPUT
}

/**
 * 问题回答表
 *
 * @property id Long id
 * @property askUser UserEntity
 * @constructor
 */
@Entity(name = "t_quest_ask")
data class QuestionAskEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @OneToOne
    @JoinColumn(name = "askUserId")
    var askUser: UserEntity,
    @ManyToOne
    val question: QuestionEntity,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "ask_item_id")
    val content: MutableList<QuestionAskItemEntity> = mutableListOf()
)

@Entity(name = "t_quest_ask_item")
data class QuestionAskItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val content: String,
    @OneToOne
    @JoinColumn(name = "questItemId")
    var questItem: QuestionItemEntity,
)