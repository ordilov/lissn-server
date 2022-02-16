package ordilov.randomplay.track.domain;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

  private final TrackStore trackStore;
  private final TrackReader trackReader;

  @Override
  public List<TrackInfo> addTracks(List<Track> tracks) {
    return trackStore.store(tracks).stream()
        .map(TrackInfo::new).collect(Collectors.toList());
  }
}
