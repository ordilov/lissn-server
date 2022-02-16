package ordilov.randomplay.track.infrastructure;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.track.domain.Track;
import ordilov.randomplay.track.domain.TrackStore;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrackStoreImpl implements TrackStore {

  private final TrackRepository trackRepository;

  @Override
  public List<Track> store(List<Track> tracks) {
    return trackRepository.saveAll(tracks);
  }

  @Override
  public Track store(Track track) {
    Optional<Track> trackOptional = trackRepository.findByResourceId(track.getResourceId());
    if(trackOptional.isEmpty()){
      return trackRepository.save(track);
    }
    return trackOptional.get();
  }
}
