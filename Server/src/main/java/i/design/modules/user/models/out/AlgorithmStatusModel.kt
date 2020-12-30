package i.design.modules.user.models.out

/**
 * 密码加密传输算法代号
 *
 */
data class AlgorithmStatusModel(
    val salt: String,
    val message: String
)
