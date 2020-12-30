package i.design.utils

import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.time.LocalDateTime

@Component
class LocalDateTimeUtils {
    private val dateFormat = SimpleDateFormat("yyy/MM/dd HH:mm:ss")
    val MIN = LocalDateTime.of(
        1970, 1, 1, 0, 0, 0
    )
    val MAX = LocalDateTime.of(
        3099, 12, 31, 0, 0, 0
    )
}