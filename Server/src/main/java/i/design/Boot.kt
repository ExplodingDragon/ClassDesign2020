package i.design

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@OpenAPIDefinition(
    info =
    Info(title = "在线问卷调查系统", version = "1.0", description = "在线问卷调查系统 API 文档 v1.0")
)
@SpringBootApplication
class Boot

fun main(args: Array<String>) {
    runApplication<Boot>(*args)
}
