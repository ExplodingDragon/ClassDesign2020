package i.design.modules.token.services

import kotlin.reflect.KClass

interface ITokenService<T : Annotation> {
    @Suppress("UNCHECKED_CAST")
    fun verifyToken0(token: String, tokenInfo: Any): Boolean {
        return verifyToken(token, tokenInfo as T)
    }

    fun verifyToken(token: String, tokenInfo: T): Boolean
    fun verifyAnnotation(): KClass<out T>

}