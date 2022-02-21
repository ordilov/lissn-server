package ordilov.randomplay.common.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @GetMapping("/")
  public String title(){
    return "Lissn API";
  }

  @GetMapping("/health")
  public String health(){
    return "OK";
  }
}
