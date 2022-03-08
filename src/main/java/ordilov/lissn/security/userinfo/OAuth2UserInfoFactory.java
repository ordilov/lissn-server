package ordilov.lissn.security.userinfo;

import ordilov.lissn.member.domain.AuthProvider;
import ordilov.lissn.security.exception.OAuth2AuthenticationProcessingException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuth2UserInfoFactory {
  public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider provider, OAuth2User oAuth2User) {
    if (provider == AuthProvider.google) {
      return new GoogleOAuth2UserInfo(oAuth2User);
    } else {
      throw new OAuth2AuthenticationProcessingException("잘못된 로그인입니다.");
    }
  }
}
