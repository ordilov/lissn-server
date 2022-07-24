package ordilov.lissn.common.adapter;

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.FormBody
import okhttp3.HttpUrl.Builder
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties
import org.springframework.stereotype.Component

@Component
class GoogleApi(
    val clientProperties: OAuth2ClientProperties
) {

    fun getAccessToken(refreshToken: String): String {
        val client = OkHttpClient()
        val refreshTokenUrl = Builder()
            .scheme("https")
            .host("oauth2.googleapis.com")
            .addPathSegment("token")
            .build();

        val registration = clientProperties.registration["google"]
        val body = FormBody.Builder()
            .add("client_id", registration!!.clientId)
            .add("client_secret", registration.clientSecret)
            .add("refresh_token", refreshToken)
            .add("grant_type", "refresh_token")
            .build();

        val refreshTokenRequest = Request.Builder()
            .url(refreshTokenUrl)
            .post(body)
            .build()

        val response = client.newCall(refreshTokenRequest).execute()
        val objectMapper = ObjectMapper()
        val googleRefreshToken =
            objectMapper.readValue(response.body!!.string(), GoogleRefreshToken::class.java)

        val accessToken = googleRefreshToken.accessToken
        println("accessToken: $accessToken")
        return accessToken
    }

}