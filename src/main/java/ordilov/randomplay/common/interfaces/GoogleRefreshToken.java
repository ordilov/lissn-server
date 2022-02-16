package ordilov.randomplay.common.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GoogleRefreshToken {

  private String scope;
  @JsonProperty("id_token")
  private String idToken;
  @JsonProperty("token_type")
  private String tokenType;
  @JsonProperty("expires_in")
  private String expiresIn;
  @JsonProperty("access_token")
  private String accessToken;
}
