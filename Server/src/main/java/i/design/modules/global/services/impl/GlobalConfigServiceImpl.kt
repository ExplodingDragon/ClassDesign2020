package i.design.modules.global.services.impl

import i.design.modules.global.models.entities.GlobalConfigEntity
import i.design.modules.global.repository.GlobalConfigRepository
import i.design.modules.global.services.IGlobalConfigService
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 带redis 缓存的配置存储
 *
 */
@Service
class GlobalConfigServiceImpl : IGlobalConfigService {
    private lateinit var redisTemplate: RedisTemplate<String, String>

    @Resource
    private lateinit var globalConfigRepository: GlobalConfigRepository

    override fun getConfig(key: String, defaultValue: String): String {
        val cacheData = redisTemplate.opsForHash<String, String>().get("design_config", key)
        if (cacheData != null) {
            return cacheData
        }
        val sqlData = globalConfigRepository.findById(key)
        return if (sqlData.isEmpty) {
            defaultValue
        } else {
            sqlData.get().data
        }
    }

    override fun setConfig(key: String, value: String): String {
        globalConfigRepository.save(GlobalConfigEntity(key, value))
        redisTemplate.opsForHash<String, String>().put("design_config", key, value)
        return value
    }
}