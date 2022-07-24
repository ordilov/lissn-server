package ordilov.lissn.member.application.service;

import MemberReader
import ordilov.lissn.member.application.port.`in`.GetMemberQuery
import ordilov.lissn.member.domain.GetMemberInfo
import org.springframework.stereotype.Service

@Service
class GetMemberService(
    val memberReader: MemberReader
) : GetMemberQuery {

    override fun getMember(memberId: Long): GetMemberInfo {
        val member = memberReader.getMemberBy(memberId)
        return GetMemberInfo(
            member.id!!,
            member.name,
            member.email,
            member.picture,
            member.provider
        )
    }

    override fun getMemberByEmail(email: String): GetMemberInfo {
        val member = memberReader.getMemberByEmail(email)
        return GetMemberInfo(
            member.id!!,
            member.name,
            member.email,
            member.picture,
            member.provider
        )
    }
}