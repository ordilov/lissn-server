package ordilov.randomplay.security.config;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

  private final OAuth2AuthorizationRequestResolver defaultAuthorizationRequestResolver;

  public CustomAuthorizationRequestResolver(
      ClientRegistrationRepository clientRegistrationRepository) {
    this.defaultAuthorizationRequestResolver =
        new DefaultOAuth2AuthorizationRequestResolver(
            clientRegistrationRepository, "/oauth2/authorize");
  }

  @Override
  public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
    OAuth2AuthorizationRequest authorizationRequest =
        this.defaultAuthorizationRequestResolver.resolve(request);

    return authorizationRequest != null ?
        customAuthorizationRequest(authorizationRequest) :
        null;
  }

  @Override
  public OAuth2AuthorizationRequest resolve(
      HttpServletRequest request, String clientRegistrationId) {

    OAuth2AuthorizationRequest authorizationRequest =
        this.defaultAuthorizationRequestResolver.resolve(
            request, clientRegistrationId);

    return authorizationRequest != null ?
        customAuthorizationRequest(authorizationRequest) :
        null;
  }

  private OAuth2AuthorizationRequest customAuthorizationRequest(
      OAuth2AuthorizationRequest authorizationRequest) {

    Map<String, Object> additionalParameters =
        new LinkedHashMap<>(authorizationRequest.getAdditionalParameters());
    additionalParameters.put("access_type", "offline");
    additionalParameters.put("prompt", "consent");

    return OAuth2AuthorizationRequest.from(authorizationRequest)
        .additionalParameters(additionalParameters)
        .build();
  }
}
