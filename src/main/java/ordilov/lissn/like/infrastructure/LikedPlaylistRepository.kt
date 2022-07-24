package ordilov.lissn.like.infrastructure

import java.util.Optional;
import ordilov.lissn.like.domain.LikedPlaylist;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.playlist.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

interface LikedPlaylistRepository : JpaRepository<LikedPlaylist, Long> {

  fun findByMemberAndPlaylist( member: Member, playlist: Playlist ): Optional<LikedPlaylist>

  fun findByMemberIdAndPlaylistId( memberId: Long, playlistId: Long ): LikedPlaylist?
}