package ordilov.lissn.security.config;

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class CustomAuthorizationRequestResolver(
    clientRegistrationRepository: ClientRegistrationRepository
) : OAuth2AuthorizationRequestResolver {

    private final val defaultAuthorizationRequestResolver: OAuth2AuthorizationRequestResolver

    init {
        defaultAuthorizationRequestResolver = DefaultOAuth2AuthorizationRequestResolver(
            clientRegistrationRepository, "/oauth2/authorize"
        )
    }

    override fun resolve(
        request: HttpServletRequest, clientRegistrationId: String
    ): OAuth2AuthorizationRequest {
        val authorizationRequest =
            defaultAuthorizationRequestResolver.resolve(
                request, clientRegistrationId
            )

        return customAuthorizationRequest(authorizationRequest)
    }

    override fun resolve(request: HttpServletRequest?): OAuth2AuthorizationRequest {
        val authorizationRequest = defaultAuthorizationRequestResolver.resolve(request)

        return customAuthorizationRequest(authorizationRequest)
    }

    private fun customAuthorizationRequest(
        authorizationRequest: OAuth2AuthorizationRequest
    ): OAuth2AuthorizationRequest {
        val additionalParameters = authorizationRequest.additionalParameters
        additionalParameters["access_type"] = "offline";
//    additionalParameters.put("prompt", "consent");

        return OAuth2AuthorizationRequest.from(authorizationRequest)
            .additionalParameters(additionalParameters)
            .build();
    }
}