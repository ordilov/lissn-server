package ordilov.lissn.member.application.service

import ordilov.lissn.member.application.port.`in`.AuthCommand
import ordilov.lissn.member.application.port.`in`.RegisterCommand
import ordilov.lissn.member.application.port.out.MemberStore
import ordilov.lissn.member.domain.GetMemberInfo
import ordilov.lissn.member.domain.Member
import ordilov.lissn.member.domain.RefreshInfo
import ordilov.lissn.security.TokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AuthCommandService(
    val memberStore: MemberStore,
    val tokenProvider: TokenProvider
) : AuthCommand {

    override fun signUp(command: RegisterCommand): GetMemberInfo {
        val member = memberStore.store(
            Member(
                command.name,
                command.email,
                command.picture,
                command.provider
            )
        )
        return GetMemberInfo(
            member.id!!,
            member.name,
            member.email,
            member.picture,
            member.provider,
        )
    }

    override fun refresh(refreshToken: String): RefreshInfo {
        val tokenInfo = tokenProvider.getTokenInfo(refreshToken)
        val accessToken = tokenProvider.createToken(tokenInfo)
        val newRefreshToken = tokenProvider.createRefreshToken(tokenInfo)
        return RefreshInfo(accessToken, newRefreshToken)
    }
}