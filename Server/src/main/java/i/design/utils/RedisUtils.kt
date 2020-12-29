package i.design.utils

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import javax.annotation.Resource


@Component
class RedisUtils {
    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>

}