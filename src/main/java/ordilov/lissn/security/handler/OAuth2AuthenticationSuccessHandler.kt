package ordilov.lissn.security.handler

import ordilov.lissn.common.adapter.CookieUtils
import ordilov.lissn.common.adapter.CookieUtils.Companion.REDIRECT_URI_PARAM_COOKIE_NAME
import ordilov.lissn.security.HttpCookieOAuth2AuthorizationRequestRepository
import ordilov.lissn.security.TokenProvider
import ordilov.lissn.security.userinfo.UserPrincipal
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class OAuth2AuthenticationSuccessHandler(
    val tokenProvider: TokenProvider,
    val oAuth2AuthorizedClientService: OAuth2AuthorizedClientService,
    val httpCookieOAuth2AuthorizationRequestRepository: HttpCookieOAuth2AuthorizationRequestRepository,
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val targetUrl = determineTargetUrl(request, response, authentication)

        if (response.isCommitted) {
            println("Response has already been committed. Unable to redirect to $targetUrl")
            return
        }

        updateRefreshToken(authentication)
        clearAuthenticationAttributes(request, response)
        redirectStrategy.sendRedirect(request, response, targetUrl)
    }

    override fun determineTargetUrl(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ): String {
        val redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
            .map(Cookie::getValue)
        val userPrincipal = authentication.principal as UserPrincipal
        val token = tokenProvider.createToken(userPrincipal.getTokenInfo())
        val refreshToken = tokenProvider.createRefreshToken(userPrincipal.getTokenInfo())
        val targetUrl = redirectUri.orElse(defaultTargetUrl)

        return UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("token", token)
            .queryParam("refreshToken", refreshToken)
            .build().toString()
    }

    fun clearAuthenticationAttributes(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(
            request,
            response
        );
    }

    fun updateRefreshToken(authentication: Authentication) {
        val principal = authentication.principal as UserPrincipal
        val user: OAuth2AuthorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(
            principal.provider.name,
            authentication.name
        );
        val refreshToken = user.refreshToken
    }

}