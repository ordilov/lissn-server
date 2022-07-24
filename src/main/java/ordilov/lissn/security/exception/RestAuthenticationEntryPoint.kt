package ordilov.lissn.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper
import ordilov.lissn.common.domain.exception.ErrorCode
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RestAuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        println("잘못된 접근입니다. Message - ${authException?.message}")

        val errorCode = request?.getAttribute("errorCode") as ErrorCode ?: ErrorCode.UNAUTHORIZED

        println("에러 코드 - ${ErrorCode.UNAUTHORIZED.message}")
        val mapper = ObjectMapper()
        val error = mapper.writeValueAsString(errorCode)

        response?.characterEncoding = StandardCharsets.UTF_8.name();
        response?.status = HttpServletResponse.SC_UNAUTHORIZED;
        response?.contentType = MediaType.APPLICATION_JSON_VALUE;
        response?.writer?.write(error);
    }
}