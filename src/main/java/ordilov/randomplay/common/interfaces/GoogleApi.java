package ordilov.randomplay.common.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Registration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleApi {

  private final OAuth2ClientProperties clientProperties;

  public String getAccessToken(String refreshToken) {
    OkHttpClient client = new OkHttpClient();
    HttpUrl refreshTokenUrl = new Builder()
        .scheme("https")
        .host("oauth2.googleapis.com")
        .addPathSegment("token")
        .build();

    Registration registration = clientProperties.getRegistration().get("google");
    RequestBody body = new FormBody.Builder()
        .add("client_id", registration.getClientId())
        .add("client_secret", registration.getClientSecret())
        .add("refresh_token", refreshToken)
        .add("grant_type", "refresh_token")
        .build();

    Request refreshTokenRequest = new Request.Builder()
        .url(refreshTokenUrl)
        .post(body)
        .build();

    String accessToken = null;
    try (Response response = client.newCall(refreshTokenRequest).execute()) {
      ObjectMapper objectMapper = new ObjectMapper();
      GoogleRefreshToken googleRefreshToken = objectMapper.readValue(
          Objects.requireNonNull(response.body()).string(), GoogleRefreshToken.class);

      accessToken = googleRefreshToken.getAccessToken();
      log.info("accessToken: {}", accessToken);
    } catch (IOException e) {
      log.error("error", e);
    }
    return accessToken;
  }

}
