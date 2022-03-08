package ordilov.lissn.like.infrastructure;

import java.util.Optional;
import ordilov.lissn.like.domain.LikedPlaylist;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.playlist.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedPlaylistRepository extends JpaRepository<LikedPlaylist, Long> {

  Optional<LikedPlaylist> findByMemberAndPlaylist(Member member, Playlist playlist);

  Optional<LikedPlaylist> findByMemberIdAndPlaylistId(Long memberId, Long playlistId);
}
