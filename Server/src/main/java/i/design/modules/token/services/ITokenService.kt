package i.design.modules.token.services

import com.github.openEdgn.logger4k.ILogger
import i.design.modules.user.models.table.UserEntity
import org.springframework.web.context.request.WebRequest
import kotlin.reflect.KClass

interface ITokenService<T : Annotation> {
    val logger: ILogger

    @Suppress("UNCHECKED_CAST")
    fun verifyToken0(token: String, tokenInfo: Any): Boolean {
        return try {
            verifyToken(token, tokenInfo as T)
        } catch (e: Exception) {
            logger.debug("token 解析错误！ {}", e.message)
            false
        }
    }

    /**
     * 校验 TOKEN
     * @param token String
     * @param tokenInfo T
     * @return Boolean
     */
    fun verifyToken(token: String, tokenInfo: T): Boolean

    /**
     * 创建 Token
     * @param user UserEntity
     * @return String
     */
    fun createToken(user: UserEntity): String


    fun verifyAnnotation(): KClass<out T>

    fun loadUserIdFromToken(webRequest: WebRequest): Long

}