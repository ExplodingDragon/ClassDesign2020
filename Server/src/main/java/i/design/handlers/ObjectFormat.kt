package i.design.handlers

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ObjectFormat {
    @Bean("jackson2ObjectMapperBuilderCustomizer")
    fun jackson2ObjectMapperBuilderCustomizer(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { jacksonObjectMapperBuilder ->
            jacksonObjectMapperBuilder.serializerByType(Long::class.javaObjectType, ToStringSerializer.instance)
                .serializerByType(Long::class.java, ToStringSerializer.instance)
        }
    }
}