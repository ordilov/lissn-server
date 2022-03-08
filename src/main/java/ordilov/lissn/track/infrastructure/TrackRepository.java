package ordilov.lissn.track.infrastructure;

import java.util.Optional;
import ordilov.lissn.track.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
  Optional<Track> findByResourceId(String resourceId);
}
