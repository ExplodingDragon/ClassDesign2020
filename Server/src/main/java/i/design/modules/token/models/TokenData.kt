package i.design.modules.token.models

import com.fasterxml.jackson.databind.ObjectMapper


val objectMapper = ObjectMapper()

data class TokenItem(
    /**
     * token 头部
     */
    val tokenHeader: TokenHeader,
    /**
     * token 个人信息
     */
    val tokenData: TokenData,
    /**
     * token 校验码
     */
    val checkValue: String
)

data class TokenData(
    /**
     * 用户 ID
     */
    val userId: Long,
    /**
     * 时间戳
     */
    val timeStamp: Long
)

data class TokenHeader(
    /**
     * 过期时间
     */
    val expiredTime: Long,
    /**
     * token ID
     */
    val id: Long
)