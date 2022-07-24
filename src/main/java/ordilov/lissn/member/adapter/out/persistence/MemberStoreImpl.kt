package ordilov.lissn.member.adapter.out.persistence

import ordilov.lissn.member.application.port.out.MemberStore
import ordilov.lissn.member.domain.Member
import org.springframework.stereotype.Repository

@Repository
class MemberStoreImpl(
    val memberRepository: MemberRepository
) : MemberStore {

    override fun store(member: Member): Member {
        return memberRepository.save(member)
    }

    override fun updateName(id: Long, name: String): Member {
        val member = memberRepository.findById(id).orElseThrow()
        member.updateName(name)
        return member
    }

    override fun updatePicture(id: Long, picture: String): Member {
        val member = memberRepository.findById(id).orElseThrow()
        member.updatePicture(picture)
        return member
    }
}