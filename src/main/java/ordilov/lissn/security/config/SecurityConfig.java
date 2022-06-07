package ordilov.lissn.security.config;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.security.HttpCookieOAuth2AuthorizationRequestRepository;
import ordilov.lissn.security.filter.TokenAuthenticationFilter;
import ordilov.lissn.security.handler.OAuth2AuthenticationFailureHandler;
import ordilov.lissn.security.handler.OAuth2AuthenticationSuccessHandler;
import ordilov.lissn.security.userinfo.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final TokenAuthenticationFilter tokenAuthenticationFilter;
  private final AuthenticationEntryPoint restAuthenticationEntryPoint;
  private final CustomAuthorizationRequestResolver customAuthorizationRequestResolver;
  private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
  private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
  private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

  @Bean
  protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http.
        cors()
        .and()
        .sessionManagement(s -> s
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .exceptionHandling(e -> e
            .authenticationEntryPoint(restAuthenticationEntryPoint))
        .authorizeRequests(a -> a
            .antMatchers("/auth/**", "/oauth2/**", "/playlists/**", "/actuator/**")
            .permitAll()
            .anyRequest().authenticated()
        )
        .oauth2Login(o -> o
            .authorizationEndpoint(a -> a
                .baseUri("/oauth2/authorize")
                .authorizationRequestResolver(customAuthorizationRequestResolver)
                .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
            )
            .redirectionEndpoint(r -> r.baseUri("/oauth2/callback/*"))
            .userInfoEndpoint(u -> u.userService(customOAuth2UserService))
            .successHandler(oAuth2AuthenticationSuccessHandler)
            .failureHandler(oAuth2AuthenticationFailureHandler)
        )
            .addFilterBefore(tokenAuthenticationFilter, OAuth2LoginAuthenticationFilter.class)
        .build();
  }

  @Bean
  public WebSecurity configure(WebSecurity web) {
    web
        .ignoring()
        .antMatchers("/v2/api-docs/**", "/swagger-resources/**",
            "/swagger-ui.html", "/webjars/**", "/swagger/**", "/h2-console/**",
            "/", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg",
            "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js", "/health");
    return web;
  }
}