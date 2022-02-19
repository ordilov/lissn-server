package ordilov.randomplay.like.infrastructure;

import java.util.Optional;
import ordilov.randomplay.like.domain.LikedPlaylist;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.playlist.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedPlaylistRepository extends JpaRepository<LikedPlaylist, Long> {

  Optional<LikedPlaylist> findByMemberAndPlaylist(Member member, Playlist playlist);

  Optional<LikedPlaylist> findByMemberIdAndPlaylistId(Long memberId, Long playlistId);
}
