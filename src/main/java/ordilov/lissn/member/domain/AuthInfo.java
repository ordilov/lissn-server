package ordilov.lissn.member.domain;

import lombok.Value;

public class AuthInfo {

  @Value
  public static class RefreshInfo{
    String accessToken;
    String refreshToken;
  }
}
