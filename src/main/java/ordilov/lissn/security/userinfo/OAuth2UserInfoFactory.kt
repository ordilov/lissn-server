package ordilov.lissn.security.userinfo;

import ordilov.lissn.member.domain.AuthProvider;
import ordilov.lissn.security.exception.OAuth2AuthenticationProcessingException;
import org.springframework.security.oauth2.core.user.OAuth2User;

class OAuth2UserInfoFactory {
    companion object {
        fun getOAuth2UserInfo(provider: AuthProvider, oAuth2User: OAuth2User): OAuth2UserInfo {
            if (provider == AuthProvider.google) {
                return GoogleOAuth2UserInfo(oAuth2User);
            } else {
                throw OAuth2AuthenticationProcessingException("잘못된 로그인입니다.");
            }
        }
    }
}