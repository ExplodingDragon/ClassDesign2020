package i.design.handlers

import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.serializer.StringRedisSerializer

import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer

import org.springframework.data.redis.core.RedisTemplate

import org.springframework.data.redis.connection.RedisConnectionFactory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean


@Configuration
@EnableCaching
class RedisConfig : CachingConfigurerSupport() {
    @Autowired
    private lateinit var redisConnectionFactory: RedisConnectionFactory

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any>{
        val redisTemplate = RedisTemplate<String, Any>()
        // 注入数据源
        redisTemplate.setConnectionFactory(redisConnectionFactory)
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        val jackson2JsonRedisSerializer: Jackson2JsonRedisSerializer<*> = Jackson2JsonRedisSerializer(Any::class.java)
        val stringRedisSerializer = StringRedisSerializer()
        // key-value结构序列化数据结构
        redisTemplate.keySerializer = stringRedisSerializer
        redisTemplate.valueSerializer = jackson2JsonRedisSerializer
        // hash数据结构序列化方式,必须这样否则存hash 就是基于jdk序列化的
        redisTemplate.hashKeySerializer = stringRedisSerializer
        redisTemplate.hashValueSerializer = jackson2JsonRedisSerializer
        // 启用默认序列化方式
        redisTemplate.isEnableDefaultSerializer = true
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer)
        return redisTemplate
    }
}