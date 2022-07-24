package ordilov.lissn.security.userinfo

import ordilov.lissn.member.application.port.`in`.AuthCommand
import ordilov.lissn.member.application.port.`in`.GetMemberQuery
import ordilov.lissn.member.application.port.`in`.RegisterCommand
import ordilov.lissn.member.domain.AuthProvider
import ordilov.lissn.security.exception.OAuth2AuthenticationProcessingException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class CustomOAuth2UserService(
    val authCommand: AuthCommand,
    val getMemberQuery: GetMemberQuery
) : DefaultOAuth2UserService() {

    override fun loadUser(oAuth2UserRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(oAuth2UserRequest)

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User)
        } catch (ex: AuthenticationException) {
            throw ex
        } catch (ex: Exception) {
            throw InternalAuthenticationServiceException(ex.message, ex.cause)
        }
    }

    private fun processOAuth2User(
        oAuth2UserRequest: OAuth2UserRequest,
        oAuth2User: OAuth2User
    ): OAuth2User {
        val provider = AuthProvider.valueOf(
            oAuth2UserRequest.clientRegistration.registrationId
        )
        val oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider, oAuth2User)

        if (!StringUtils.hasText(oAuth2UserInfo.email)) {
            throw OAuth2AuthenticationProcessingException("OAuth2 인증 정보에 이메일이 존재하지 않습니다.")
        }

        val memberInfo =
            getMemberQuery.getMemberByEmail(oAuth2UserInfo.email) ?: authCommand.signUp(
                RegisterCommand(
                    oAuth2UserInfo.id,
                    oAuth2UserInfo.name,
                    oAuth2UserInfo.email,
                    oAuth2UserInfo.picture,
                    provider
                )
            )

        return UserPrincipal(
            memberInfo.id,
            memberInfo.provider,
            oAuth2User.attributes,
            oAuth2User.authorities
        )
    }
}