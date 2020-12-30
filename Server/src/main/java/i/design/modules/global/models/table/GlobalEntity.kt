package i.design.modules.global.models.table

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "t_global_config")
data class GlobalEntity(
    @Id
    var key: String,
    @Column(nullable = false, length = 8192)
    val data: String

)