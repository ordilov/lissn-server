package ordilov.lissn.track.domain;

import java.util.List;

public interface TrackService {
  List<TrackInfo> addTracks(List<Track> tracks);
}
