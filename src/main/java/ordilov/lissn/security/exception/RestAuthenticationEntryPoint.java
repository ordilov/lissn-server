package ordilov.lissn.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.common.exception.ErrorCode;
import ordilov.lissn.common.interfaces.CommonResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException e) throws IOException {
    log.error("잘못된 접근입니다. Message - {}", e.getMessage());

    ErrorCode errorCode = (ErrorCode) request.getAttribute("errorCode");
    if (errorCode == null) {
      errorCode = ErrorCode.UNAUTHORIZED;
    }

    ObjectMapper mapper = new ObjectMapper();
    String error = mapper.writeValueAsString(CommonResponse.fail(errorCode));

    response.setCharacterEncoding("UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter().write(error);
  }
}
