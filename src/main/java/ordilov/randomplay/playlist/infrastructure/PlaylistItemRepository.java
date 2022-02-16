package ordilov.randomplay.playlist.infrastructure;

import ordilov.randomplay.playlist.domain.PlaylistItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistItemRepository extends JpaRepository<PlaylistItem, Long> {

}
