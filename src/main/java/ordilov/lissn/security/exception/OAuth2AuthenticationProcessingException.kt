package ordilov.lissn.security.exception;

import org.springframework.security.core.AuthenticationException

class OAuth2AuthenticationProcessingException(
    message: String
) : AuthenticationException(message) {

}