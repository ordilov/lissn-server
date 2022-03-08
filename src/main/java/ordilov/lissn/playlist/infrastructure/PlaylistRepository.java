package ordilov.lissn.playlist.infrastructure;

import java.util.List;
import ordilov.lissn.playlist.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

  List<Playlist> findByMemberId(Long memberId);
}
