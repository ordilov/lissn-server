package ordilov.lissn.security.config

import ordilov.lissn.security.HttpCookieOAuth2AuthorizationRequestRepository
import ordilov.lissn.security.filter.TokenAuthenticationFilter
import ordilov.lissn.security.handler.OAuth2AuthenticationFailureHandler
import ordilov.lissn.security.handler.OAuth2AuthenticationSuccessHandler
import ordilov.lissn.security.userinfo.CustomOAuth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    val customOAuth2UserService: CustomOAuth2UserService,
    val tokenAuthenticationFilter: TokenAuthenticationFilter,
    val restAuthenticationEntryPoint: AuthenticationEntryPoint,
    val customAuthorizationRequestResolver: CustomAuthorizationRequestResolver,
    val oAuth2AuthenticationSuccessHandler: OAuth2AuthenticationSuccessHandler,
    val oAuth2AuthenticationFailureHandler: OAuth2AuthenticationFailureHandler,
    val httpCookieOAuth2AuthorizationRequestRepository: HttpCookieOAuth2AuthorizationRequestRepository
) {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .exceptionHandling { it.authenticationEntryPoint(restAuthenticationEntryPoint) }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeRequests {
                it.antMatchers(
                    "/auth/**",
                    "/oauth2/**",
                    "/playlists/**",
                    "/actuator/**"
                ).permitAll()
            }
            .oauth2Login {
                it.authorizationEndpoint {
                    it.baseUri("/oauth2/authorize")
                        .authorizationRequestResolver(customAuthorizationRequestResolver)
                        .authorizationRequestRepository(
                            httpCookieOAuth2AuthorizationRequestRepository
                        )
                }
                    .redirectionEndpoint { it.baseUri("/oauth2/callback/*") }
                    .userInfoEndpoint { it.userService(customOAuth2UserService) }
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler)
            }
            .addFilterBefore(tokenAuthenticationFilter, OAuth2LoginAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun configure(web: WebSecurity): WebSecurity {
        web
            .ignoring()
            .antMatchers(
                "/v2/api-docs/**", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**", "/h2-console/**",
                "/", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg",
                "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js", "/health"
            )
        return web
    }
}