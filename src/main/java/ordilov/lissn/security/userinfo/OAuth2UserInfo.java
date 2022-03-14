package ordilov.lissn.security.userinfo;

import static ordilov.lissn.member.application.port.in.MemberCommand.*;

import lombok.Getter;
import ordilov.lissn.member.domain.AuthProvider;

@Getter
public abstract class OAuth2UserInfo {

  protected String id;
  protected String name;
  protected String email;
  protected String picture;
  protected AuthProvider provider;

  public RegisterCommand of() {
    return new RegisterCommand(id, name, email, picture, provider);
  }
}
