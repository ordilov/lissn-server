package ordilov.lissn.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.common.exception.ErrorCode;
import ordilov.lissn.security.TokenProvider;
import ordilov.lissn.security.userinfo.UserPrincipal;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private static final String TOKEN_TYPE = "Bearer ";
  private final TokenProvider tokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {

    String jwt = getJwtFromRequest(request);
    if (!StringUtils.hasText(jwt)) {
      filterChain.doFilter(request, response);
      return;
    }
    ErrorCode errorCode = null;
    try {
      tokenProvider.validateToken(jwt);
      Claims claims = tokenProvider.getClaims(jwt);
      UserPrincipal userPrincipal = new UserPrincipal(claims);
      OAuth2AuthenticationToken authentication = new OAuth2AuthenticationToken(userPrincipal,
          null, "google");
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (SecurityException | MalformedJwtException | UnsupportedJwtException ex) {
      errorCode = ErrorCode.INVALID_TOKEN;
    } catch (ExpiredJwtException ex) {
      errorCode = ErrorCode.EXPIRED_TOKEN;
    }
    request.setAttribute("errorCode", errorCode);
    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_TYPE)) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
