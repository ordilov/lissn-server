package ordilov.lissn.like.infrastructure

import ordilov.lissn.like.domain.LikedTrack
import ordilov.lissn.member.domain.Member
import ordilov.lissn.track.domain.Track
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LikedTrackRepository : JpaRepository<LikedTrack, Long> {
    fun findByMemberAndTrack(member: Member, track: Track): Optional<LikedTrack>
}