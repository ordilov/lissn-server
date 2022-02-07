package ordilov.randomplay.security.config;

import lombok.RequiredArgsConstructor;
import ordilov.randomplay.security.userinfo.CustomOAuth2UserService;
import ordilov.randomplay.security.HttpCookieOAuth2AuthorizationRequestRepository;
import ordilov.randomplay.security.handler.OAuth2AuthenticationFailureHandler;
import ordilov.randomplay.security.handler.OAuth2AuthenticationSuccessHandler;
import ordilov.randomplay.security.filter.TokenAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final TokenAuthenticationFilter tokenAuthenticationFilter;
  private final AuthenticationEntryPoint restAuthenticationEntryPoint;
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
        .antMatchers("/",
            "/error",
            "/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js")
        .permitAll()
        .antMatchers("/auth/**", "/oauth2/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2Login()
        .authorizationEndpoint()
        .baseUri("/oauth2/authorize")
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

//    http.addFilterBefore(tokenAuthenticationFilter, OAuth2LoginAuthenticationFilter.class);
    http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}