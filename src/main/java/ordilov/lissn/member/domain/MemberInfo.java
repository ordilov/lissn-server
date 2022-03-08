package ordilov.lissn.member.domain;

import lombok.Value;

@Value
public class MemberInfo {
  Long id;
  String name;
  String email;
  String refreshToken;
  String profileImageUrl;
  AuthProvider provider;

  public MemberInfo(Member member) {
    this.id = member.getId();
    this.name = member.getName();
    this.email = member.getEmail();
    this.provider = member.getProvider();
    this.refreshToken = member.getRefreshToken();
    this.profileImageUrl = member.getProfileImageUrl();
  }
}
