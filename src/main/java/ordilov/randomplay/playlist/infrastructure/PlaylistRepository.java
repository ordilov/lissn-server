package ordilov.randomplay.playlist.infrastructure;

import java.util.List;
import ordilov.randomplay.playlist.domain.Playlist;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

  List<Playlist> findByMemberId(Long memberId);
}
