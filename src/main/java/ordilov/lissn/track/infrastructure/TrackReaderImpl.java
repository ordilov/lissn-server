package ordilov.lissn.track.infrastructure;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.track.domain.Track;
import ordilov.lissn.track.domain.TrackReader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrackReaderImpl implements TrackReader {

  private final TrackRepository trackRepository;

  @Override
  public Track getTrackBy(Long id) {
    return trackRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException("Track with id " + id + " not found")
    );
  }
}
