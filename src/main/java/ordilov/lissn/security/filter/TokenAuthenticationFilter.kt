package ordilov.lissn.security.filter

import io.jsonwebtoken.ExpiredJwtException
import ordilov.lissn.common.domain.exception.ErrorCode
import ordilov.lissn.security.TokenProvider
import ordilov.lissn.security.userinfo.UserPrincipal
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class TokenAuthenticationFilter(
    val tokenProvider: TokenProvider
) : OncePerRequestFilter() {

    val TOKEN_TYPE: String = "Bearer "

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = getJwtFromRequest(request)
        if (!StringUtils.hasText(jwt)) {
            filterChain.doFilter(request, response)
            return
        }
        var errorCode: ErrorCode? = null
        try {
            tokenProvider.validateToken(jwt)
            val tokenInfo = tokenProvider.getTokenInfo(jwt)
            val userPrincipal = UserPrincipal(tokenInfo)
            val authentication = OAuth2AuthenticationToken(
                userPrincipal,
                null, "google"
            );
            authentication.setDetails(WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (e: Exception) {
            errorCode = when (e) {
                is ExpiredJwtException -> ErrorCode.EXPIRED_TOKEN
                else -> ErrorCode.INVALID_TOKEN
            }
        }
        request.setAttribute("errorCode", errorCode)
        filterChain.doFilter(request, response)
    }

    fun getJwtFromRequest(request: HttpServletRequest): String {
        val bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_TYPE)) {
            return bearerToken.substring(7)
        }
        return ""
    }
}