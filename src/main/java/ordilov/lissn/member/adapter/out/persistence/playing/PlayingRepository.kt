package ordilov.lissn.member.adapter.out.persistence.playing

import ordilov.lissn.member.domain.playing.Playing
import org.springframework.data.jpa.repository.JpaRepository

interface PlayingRepository : JpaRepository<Playing, Long> {

    fun deleteByMemberId(memberId: Long)

    fun findByMemberId(memberId: Long): List<Playing>
}