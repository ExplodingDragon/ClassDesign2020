package i.design.modules.token.services

import com.github.openEdgn.logger4k.ILogger
import kotlin.reflect.KClass

interface ITokenService<T : Annotation> {
    val logger: ILogger

    @Suppress("UNCHECKED_CAST")
    fun verifyToken0(token: String, tokenInfo: Any): Boolean {
        return try {
            verifyToken(token, tokenInfo as T)
        } catch (e: Exception) {
            logger.warn("token 解析错误！ {}", e.message)
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
     * 根据 TOKEN 得到用户信息
     * @param token String
     * @return Long
     */
    fun getUserIdByToken(token: String): Long

    /**
     * 根据用户创建 token
     * @param id Long
     * @return String
     */
    fun createTokenByUserId(id: Long): String

    /**
     *  监听的注解
     */
    val verifyAnnotation: KClass<out T>

}