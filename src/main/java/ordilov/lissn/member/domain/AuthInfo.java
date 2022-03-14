package ordilov.lissn.member.domain;

import lombok.Value;

public class AuthInfo {

  @Value
  public static class TokenInfo {
    Long id;
    String name;
    String email;
    String picture;
    AuthProvider provider;

    public TokenInfo(Member member) {
      this.id = member.getId();
      this.name = member.getName();
      this.email = member.getEmail();
      this.picture = member.getPicture();
      this.provider = member.getProvider();
    }

    public TokenInfo(Long id, String name, String email, String picture, AuthProvider provider) {
      this.id = id;
      this.name = name;
      this.email = email;
      this.picture = picture;
      this.provider = provider;
    }
  }

  @Value
  public static class RefreshInfo {

    String accessToken;
    String refreshToken;
  }
}
