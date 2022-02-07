package ordilov.randomplay.config;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "auth")
public class AppProperties {

  private final String tokenSecret;
  private final Long tokenExpiration;
  private final List<String> authorizedRedirectUrls;

}
