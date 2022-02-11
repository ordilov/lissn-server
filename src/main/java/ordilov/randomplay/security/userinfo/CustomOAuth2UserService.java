package ordilov.randomplay.security.userinfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.member.application.MemberFacade;
import ordilov.randomplay.member.domain.AuthProvider;
import ordilov.randomplay.member.domain.MemberInfo;
import ordilov.randomplay.security.exception.OAuth2AuthenticationProcessingException;
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

  private final MemberFacade memberFacade;

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

    MemberInfo memberInfo = memberFacade.login(oAuth2UserInfo.of());
    return new UserPrincipal(memberInfo, oAuth2User);
  }
}
