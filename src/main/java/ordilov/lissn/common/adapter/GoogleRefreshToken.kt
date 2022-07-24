package ordilov.lissn.common.adapter

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleRefreshToken(
    val scope: String,
    @JsonProperty("id_token") val idToken: String,
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("expires_in") val expiresIn: String,
    @JsonProperty("access_token") val accessToken: String
)