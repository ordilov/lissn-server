package ordilov.lissn.common;

import java.util.Collections;
import java.util.Map;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.member.domain.MemberInfo;
import ordilov.lissn.security.userinfo.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements
    WithSecurityContextFactory<WithMockCustomUser> {

  @Override
  public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();

    Member member = MemberTestData.defaultMember();
    MemberInfo memberInfo = new MemberInfo(member);

    Map<String, Object> attributes = Collections.singletonMap("memberInfo", memberInfo);

    UserPrincipal principal = new UserPrincipal(memberInfo.getId(), memberInfo.getProvider(),
        attributes, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    Authentication auth = new OAuth2AuthenticationToken(principal,
        null, "google");
    context.setAuthentication(auth);
    return context;
  }
}
