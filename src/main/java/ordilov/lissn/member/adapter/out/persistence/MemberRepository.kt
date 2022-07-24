package ordilov.lissn.member.adapter.out.persistence

import ordilov.lissn.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByEmail(email: String): Optional<Member>
    fun existsByEmail(email: String): Boolean
}