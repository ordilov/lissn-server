package ordilov.lissn.security

import ordilov.lissn.common.adapter.CookieUtils
import ordilov.lissn.common.adapter.CookieUtils.Companion.OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME
import ordilov.lissn.common.adapter.CookieUtils.Companion.REDIRECT_URI_PARAM_COOKIE_NAME
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class HttpCookieOAuth2AuthorizationRequestRepository :
    AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    val COOKIE_EXPIRE_SECONDS = 180

    fun removeAuthorizationRequestCookies(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
        CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME)
    }

    override fun loadAuthorizationRequest(request: HttpServletRequest): OAuth2AuthorizationRequest {
        return CookieUtils.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
            .map { cookie ->
                CookieUtils.deserialize(
                    cookie,
                    OAuth2AuthorizationRequest::class.java
                )
            }
            .orElse(null)
    }

    override fun removeAuthorizationRequest(request: HttpServletRequest): OAuth2AuthorizationRequest {
        return loadAuthorizationRequest(request)
    }

    override fun saveAuthorizationRequest(
        authorizationRequest: OAuth2AuthorizationRequest,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        CookieUtils.addCookie(
            response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME,
            CookieUtils.serialize(authorizationRequest), COOKIE_EXPIRE_SECONDS
        )

        val redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME)
        if (redirectUriAfterLogin.isNotBlank()) {
            CookieUtils.addCookie(
                response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin,
                COOKIE_EXPIRE_SECONDS
            )
        }
    }
}