package ordilov.randomplay.security.userinfo;

import ordilov.randomplay.member.domain.AuthProvider;
import ordilov.randomplay.security.exception.OAuth2AuthenticationProcessingException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuth2UserInfoFactory {
  public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, OAuth2User oAuth2User) {
    if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
      return new GoogleOAuth2UserInfo(oAuth2User);
    } else {
      throw new OAuth2AuthenticationProcessingException("잘못된 로그인입니다.");
    }
  }
}
