package i.design.handlers.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class RSAKeyPair(
    @Value("\${app.security.publicKey}")
    val publicKey: String,
    @Value("\${app.security.privateKey}")
    val privateKey: String
)