package ordilov.randomplay.security.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("GET","POST", "PUT", "DELETE", "OPTIONS")
        .allowedOrigins("https://lissn.vercel.app", "http://localhost:3000");
  }
}
