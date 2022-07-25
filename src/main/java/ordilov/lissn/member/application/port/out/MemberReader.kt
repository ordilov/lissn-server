package ordilov.lissn.member.application.port.out

import ordilov.lissn.member.domain.Member

interface MemberReader {

    fun getMemberBy(id: Long): Member

    fun getMemberByEmail(email: String): Member
}