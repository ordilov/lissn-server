package ordilov.lissn.security.userinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ordilov.lissn.member.domain.AuthInfo.TokenInfo;
import ordilov.lissn.member.domain.AuthProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@AllArgsConstructor
public class UserPrincipal implements OAuth2User {
  private final Long id;
  private final AuthProvider provider;
  private final Map<String, Object> attributes;
  private final Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(TokenInfo tokenInfo) {
    ObjectMapper mapper = new ObjectMapper();
    this.id = tokenInfo.getId();
    this.provider = tokenInfo.getProvider();
    this.authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    this.attributes = mapper.convertValue(tokenInfo, Map.class);
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

  public TokenInfo getTokenInfo() {
    return new TokenInfo(id,
        attributes.get("name").toString(),
        attributes.get("email").toString(),
        attributes.get("picture").toString(),
        provider);
  }
}
