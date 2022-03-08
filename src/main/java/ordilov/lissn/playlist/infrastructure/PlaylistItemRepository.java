package ordilov.lissn.playlist.infrastructure;

import ordilov.lissn.playlist.domain.PlaylistItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistItemRepository extends JpaRepository<PlaylistItem, Long> {

}
