package ordilov.lissn.member.application.port.`in`

import ordilov.lissn.member.domain.GetMemberInfo

interface GetMemberQuery {

    fun getMember(memberId: Long): GetMemberInfo

    fun getMemberByEmail(email: String): GetMemberInfo?
}