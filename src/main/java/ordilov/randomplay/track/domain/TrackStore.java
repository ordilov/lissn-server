package ordilov.randomplay.track.domain;

import java.util.List;

public interface TrackStore {

  List<Track> store(List<Track> tracks);

  Track store(Track track);
}
