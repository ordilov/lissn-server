package ordilov.lissn.security.config;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.security.HttpCookieOAuth2AuthorizationRequestRepository;
import ordilov.lissn.security.filter.TokenAuthenticationFilter;
import ordilov.lissn.security.handler.OAuth2AuthenticationFailureHandler;
import ordilov.lissn.security.handler.OAuth2AuthenticationSuccessHandler;
import ordilov.lissn.security.userinfo.CustomOAuth2UserService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.AuthenticationEntryPoint;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final TokenAuthenticationFilter tokenAuthenticationFilter;
  private final AuthenticationEntryPoint restAuthenticationEntryPoint;
  private final CustomAuthorizationRequestResolver customAuthorizationRequestResolver;
  private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
  private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
  private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
        cors()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf()
        .disable()
        .formLogin()
        .disable()
        .httpBasic()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .authorizeRequests()
        .antMatchers("/auth/**", "/oauth2/**", "/playlists/random")
        .permitAll()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2Login()
        .authorizationEndpoint()
        .baseUri("/oauth2/authorize")
        .authorizationRequestResolver(customAuthorizationRequestResolver)
        .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
        .and()
        .redirectionEndpoint()
        .baseUri("/oauth2/callback/*")
        .and()
        .userInfoEndpoint()
        .userService(customOAuth2UserService)
        .and()
        .successHandler(oAuth2AuthenticationSuccessHandler)
        .failureHandler(oAuth2AuthenticationFailureHandler);

    http.addFilterBefore(tokenAuthenticationFilter, OAuth2LoginAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) {
    web
        .ignoring()
        .antMatchers("/v2/api-docs/**", "/swagger-resources/**",
            "/swagger-ui.html", "/webjars/**", "/swagger/**", "/h2-console/**",
            "/", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg",
            "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js", "/health");
  }
}