package i.design.modules.token.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.open_edgn.security4k.asymmetric.rsa.RsaPrivate
import com.github.open_edgn.security4k.asymmetric.rsa.RsaPublic
import com.github.open_edgn.security4k.base64.base64Decode
import com.github.open_edgn.security4k.base64.base64Encode
import i.design.handlers.jwt.RSAKeyPair
import i.design.modules.token.models.TokenData
import i.design.modules.token.models.TokenHeader
import i.design.modules.token.models.TokenItem
import i.design.modules.user.entities.UserEntity
import i.design.utils.SnowFlake
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class TokenUtils {
    val mapper = ObjectMapper()

    @Autowired
    private lateinit var rsaKeyPair: RSAKeyPair

    @Autowired
    private lateinit var snowFlake: SnowFlake

    val rsaPrivate by lazy {
        RsaPrivate(rsaKeyPair.privateKey)
    }
    val rsaPublic by lazy {
        RsaPublic(rsaKeyPair.publicKey)
    }


    fun decode(token: String): TokenItem {
        val list = token.split(".").toMutableList()
        if (list.size != 3) {
            throw RuntimeException("token 格式错误:$token")
        }
        if (rsaPublic.verifyText("${list[0]}.${list[1]}", list[2])) {
            val header = mapper.readValue(list[0].base64Decode(), TokenHeader::class.javaObjectType)
            val data = mapper.readValue(rsaPublic.decodeText(list[1]), TokenData::class.javaObjectType)
            return TokenItem(header, data, list[3])
        } else {
            throw RuntimeException("token 校验失败")
        }

    }

    @Suppress("INTEGER_OVERFLOW")
    private val seekDate = 1000 * 60 * 60 * 24 * 365

    /**
     * 创建一个 Token
     * @param entity UserEntity 用户实体类
     * @return String
     */
    fun create(entity: UserEntity): String {
        val l = System.currentTimeMillis()
        val header = TokenHeader(expiredTime = l + seekDate, id = snowFlake.nextId())
        val data = TokenData(entity.id, l)
        val builder = StringBuilder()
        val h1 = mapper.writeValueAsString(header).base64Encode()
        val h2 = rsaPrivate.encodeText(mapper.writeValueAsString(data))
            .replace(Regex("([\r\n])"), "")
        builder.append(h1)
            .append(".")
            .append(h2)
            .append(".").append(rsaPrivate.signText("$h1.$h2"))
        return builder.toString()
    }
}