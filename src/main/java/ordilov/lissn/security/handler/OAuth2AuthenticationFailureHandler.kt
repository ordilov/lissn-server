package ordilov.lissn.security.handler;

import ordilov.lissn.common.adapter.CookieUtils
import ordilov.lissn.common.adapter.CookieUtils.Companion.REDIRECT_URI_PARAM_COOKIE_NAME
import ordilov.lissn.security.HttpCookieOAuth2AuthorizationRequestRepository
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class OAuth2AuthenticationFailureHandler(
    val httpCookieOAuth2AuthorizationRequestRepository: HttpCookieOAuth2AuthorizationRequestRepository
) : SimpleUrlAuthenticationFailureHandler() {

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        var targetUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
            .map(Cookie::getValue)
            .orElse(("/"))

        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("error", exception.localizedMessage)
            .build().toUriString();
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(
            request,
            response
        )

        redirectStrategy.sendRedirect(request, response, targetUrl)
    }
}