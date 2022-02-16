package ordilov.randomplay.playlist.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ordilov.randomplay.playlist.domain.Playlist;
import ordilov.randomplay.track.domain.Track;

@Getter
@NoArgsConstructor
public class PlaylistTrackDto {

  Playlist playlist;
  Track track;
}
