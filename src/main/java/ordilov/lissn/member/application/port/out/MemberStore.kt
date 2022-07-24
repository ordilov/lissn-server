package ordilov.lissn.member.application.port.out;

import ordilov.lissn.member.domain.Member;

interface MemberStore {
    fun store(member: Member): Member

    fun updateName(id: Long, name: String): Member

    fun updatePicture(id: Long, picture: String): Member
}