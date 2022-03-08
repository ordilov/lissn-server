package ordilov.lissn.security.userinfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.member.application.port.in.AuthCommand;
import ordilov.lissn.member.application.port.in.GetMemberQuery;
import ordilov.lissn.member.domain.AuthProvider;
import ordilov.lissn.member.domain.MemberInfo;
import ordilov.lissn.security.exception.OAuth2AuthenticationProcessingException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final AuthCommand authCommand;
  private final GetMemberQuery getMemberQuery;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
      throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

    try {
      return processOAuth2User(oAuth2UserRequest, oAuth2User);
    } catch (AuthenticationException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
    }
  }

  private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
    AuthProvider provider = AuthProvider.valueOf(
        oAuth2UserRequest.getClientRegistration().getRegistrationId());
    OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider, oAuth2User);

    if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
      throw new OAuth2AuthenticationProcessingException("OAuth2 인증 정보에 이메일이 존재하지 않습니다.");
    }

    MemberInfo memberInfo = getMemberQuery.getMemberByEmail(oAuth2UserInfo.getEmail())
        .orElseGet(() -> authCommand.signUp(oAuth2UserInfo.of()));

    return new UserPrincipal(memberInfo.getId(), memberInfo.getProvider(),
        oAuth2User.getAttributes(), oAuth2User.getAuthorities());
  }
}
