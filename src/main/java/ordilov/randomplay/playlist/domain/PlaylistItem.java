package ordilov.randomplay.playlist.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ordilov.randomplay.track.domain.Track;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class PlaylistItem {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "playlist_id")
  private Playlist playlist;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "track_id")
  private Track track;

  public PlaylistItem(Playlist playlist, Track track) {
    this.playlist = playlist;
    this.track = track;
  }
}
