package ordilov.lissn.security.handler;

import static ordilov.lissn.common.interfaces.CookieUtils.REDIRECT_URI_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.common.interfaces.CookieUtils;
import ordilov.lissn.security.HttpCookieOAuth2AuthorizationRequestRepository;
import ordilov.lissn.security.TokenProvider;
import ordilov.lissn.security.userinfo.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final TokenProvider tokenProvider;
  private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
  private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {

    String targetUrl = determineTargetUrl(request, response, authentication);

    if (response.isCommitted()) {
      log.debug("Response has already been committed. Unable to redirect to {}", targetUrl);
      return;
    }

    updateRefreshToken(authentication);
    clearAuthenticationAttributes(request, response);
    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }

  protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
        .map(Cookie::getValue);
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    String token = tokenProvider.createToken(userPrincipal.getTokenInfo());
    String refreshToken = tokenProvider.createRefreshToken(userPrincipal.getTokenInfo());
    String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

    return UriComponentsBuilder.fromUriString(targetUrl)
        .queryParam("token", token)
        .queryParam("refreshToken", refreshToken)
        .build().toString();
  }

  protected void clearAuthenticationAttributes(HttpServletRequest request,
      HttpServletResponse response) {
    super.clearAuthenticationAttributes(request);
    httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request,
        response);
  }

  private void updateRefreshToken(Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    OAuth2AuthorizedClient user = oAuth2AuthorizedClientService.loadAuthorizedClient(
        principal.getProvider().name(),
        authentication.getName());
    OAuth2RefreshToken refreshToken = user.getRefreshToken();
  }

}
