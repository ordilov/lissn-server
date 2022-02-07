package ordilov.randomplay.security.userinfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberReader;
import ordilov.randomplay.member.domain.MemberStore;
import ordilov.randomplay.security.exception.OAuth2AuthenticationProcessingException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final MemberStore memberStore;
  private final MemberReader memberReader;

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
    String provider = oAuth2UserRequest.getClientRegistration().getRegistrationId();
    OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider, oAuth2User);
    if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
      throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
    }

    Optional<Member> memberOptional = memberReader.getMemberByEmail(oAuth2UserInfo.getEmail());
    memberOptional.ifPresentOrElse(
        member -> updateExistingUser(member, oAuth2UserInfo),
        () -> registerMember(oAuth2UserRequest, oAuth2UserInfo)
    );

    Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
    attributes.put("provider", provider);
    return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, "name");
  }

  private void registerMember(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
    memberStore.store(
        oAuth2UserInfo.toEntity(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
  }

  private void updateExistingUser(Member existingUser, OAuth2UserInfo oAuth2UserInfo) {
    memberStore.update(existingUser, oAuth2UserInfo.of());
  }

}
