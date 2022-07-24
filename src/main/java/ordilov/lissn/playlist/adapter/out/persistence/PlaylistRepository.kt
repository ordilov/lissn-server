package ordilov.lissn.playlist.adapter.out.persistence

import ordilov.lissn.playlist.domain.Playlist
import org.springframework.data.jpa.repository.JpaRepository

interface PlaylistRepository : JpaRepository<Playlist, Long> {

    fun findByMemberId(memberId: Long): List<Playlist>
}