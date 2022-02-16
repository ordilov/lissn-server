package ordilov.randomplay.track.infrastructure;

import java.util.Optional;
import ordilov.randomplay.track.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
  Optional<Track> findByResourceId(String resourceId);
}
