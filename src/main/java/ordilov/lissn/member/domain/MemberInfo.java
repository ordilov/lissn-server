package ordilov.lissn.member.domain;

import lombok.Value;

public class MemberInfo {

  @Value
  public static class GetMemberInfo {
    Long id;
    String name;
    String email;
    String picture;
    AuthProvider provider;

    public GetMemberInfo(Member member) {
      this.id = member.getId();
      this.name = member.getName();
      this.email = member.getEmail();
      this.picture = member.getPicture();
      this.provider = member.getProvider();
    }
  }

  @Value
  public static class UpdateMemberInfo {
    Long id;
    String name;
    String email;
    String picture;
    String accessToken;
    String refreshToken;

    public UpdateMemberInfo(Member member, String accessToken, String refreshToken) {
      this.id = member.getId();
      this.name = member.getName();
      this.email = member.getEmail();
      this.picture = member.getPicture();
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
    }
  }
}
