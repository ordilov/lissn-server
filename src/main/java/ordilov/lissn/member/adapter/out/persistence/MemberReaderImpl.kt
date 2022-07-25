package ordilov.lissn.member.adapter.out.persistence

import ordilov.lissn.member.application.port.out.MemberReader
import ordilov.lissn.member.domain.Member
import org.springframework.stereotype.Component

@Component
class MemberReaderImpl(
    val memberRepository: MemberRepository
) : MemberReader {

    override fun getMemberBy(id: Long): Member {
        return memberRepository.findById(id).orElseThrow()
    }

    override fun getMemberByEmail(email: String): Member {
        return memberRepository.findByEmail(email).orElseThrow()
    }
}