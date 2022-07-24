package ordilov.lissn.security.userinfo;

import ordilov.lissn.member.domain.AuthProvider
import org.springframework.security.oauth2.core.user.OAuth2User

class GoogleOAuth2UserInfo(
    id: String,
    name: String,
    email: String,
    picture: String,
    provider: AuthProvider
) : OAuth2UserInfo(
    id, name, email, picture, provider
) {

    constructor(oAuth2User: OAuth2User) : this(
        oAuth2User.attributes["sub"] as String,
        oAuth2User.attributes["name"] as String,
        oAuth2User.attributes["email"] as String,
        oAuth2User.attributes["picture"] as String,
        AuthProvider.google
    )
}