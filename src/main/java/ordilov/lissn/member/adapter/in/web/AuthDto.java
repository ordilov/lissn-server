package ordilov.lissn.member.adapter.in.web;

import lombok.Getter;

public class AuthDto {

  @Getter
  public static class RefreshTokenDto{
    private String refreshToken;
  }
}
