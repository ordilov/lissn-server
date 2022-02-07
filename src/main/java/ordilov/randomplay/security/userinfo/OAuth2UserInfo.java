package ordilov.randomplay.security.userinfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import ordilov.randomplay.member.domain.AuthProvider;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberCommand;

@Getter
public abstract class OAuth2UserInfo {

  protected String id;

  protected String name;

  protected String email;

  protected String picture;

  public Member toEntity(String provider){
    return Member.builder()
        .name(name)
        .email(email)
        .provider(AuthProvider.valueOf(provider))
        .profileImageUrl(picture)
        .build();
  }

  public Claims toClaims() {
    Claims claims = Jwts.claims();
    claims.put("id", id);
    claims.put("name", name);
    claims.put("email", email);
    claims.put("picture", picture);
    return claims;
  }

  public MemberCommand of(){
    return MemberCommand.builder()
        .name(name)
        .email(email)
        .profileImageUrl(picture)
        .build();
  }
}
