package ordilov.lissn.security.userinfo;

import ordilov.lissn.member.domain.AuthProvider

open class OAuth2UserInfo(
    val id: String,
    val name: String,
    val email: String,
    val picture: String,
    val provider: AuthProvider
) {
}