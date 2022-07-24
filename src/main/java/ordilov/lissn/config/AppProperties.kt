package ordilov.lissn.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "auth")
data class AppProperties(
    var tokenSecret: String,
    var tokenExpiration: Long,
    var tokenRefreshExpiration: Long,
//    val authorizedRedirectUrls: List<String>
) {
    constructor() : this("", 0, 0)

    init {
        println("tokenSecret $tokenSecret")
    }
}