package ordilov.lissn.security.userinfo;

import lombok.Getter;
import ordilov.lissn.member.domain.AuthProvider;
import ordilov.lissn.member.application.port.in.MemberCommand;

@Getter
public abstract class OAuth2UserInfo {
  protected String id;
  protected String name;
  protected String email;
  protected String picture;
  protected AuthProvider provider;

  public MemberCommand of() {
    return MemberCommand.builder()
        .name(name)
        .email(email)
        .provider(provider)
        .profileImageUrl(picture)
        .build();
  }
}
