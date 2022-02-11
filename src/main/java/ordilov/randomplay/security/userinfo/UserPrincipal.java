package ordilov.randomplay.security.userinfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.Getter;
import ordilov.randomplay.member.domain.AuthProvider;
import ordilov.randomplay.member.domain.MemberInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class UserPrincipal implements OAuth2User {

  private final Long id;
  private final AuthProvider provider;
  private final Map<String, Object> attributes;
  private final Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(MemberInfo memberInfo, OAuth2User oAuth2User) {
    this.id = memberInfo.getId();
    this.provider = memberInfo.getProvider();
    this.attributes = oAuth2User.getAttributes();
    this.authorities = oAuth2User.getAuthorities();
  }

  public UserPrincipal(Claims claims) {
    this.id = Long.parseLong(claims.get("id").toString());
    this.provider = AuthProvider.valueOf(claims.get("provider").toString());
    this.authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    this.attributes = claims.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getName() {
    return String.valueOf(id);
  }

  public Claims toClaims() {
    Claims claims = Jwts.claims();
    claims.put("id", id);
    claims.put("provider", provider.name());
    claims.put("name", attributes.get("name"));
    claims.put("email", attributes.get("email"));
    claims.put("picture", attributes.get("picture"));
    return claims;
  }
}
