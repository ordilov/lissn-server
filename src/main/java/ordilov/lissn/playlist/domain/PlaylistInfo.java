package ordilov.lissn.playlist.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ordilov.lissn.member.domain.playing.Playing;
import ordilov.lissn.track.domain.Track;

@Getter
public class PlaylistInfo {

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Main {
    private Long id;
    private String title;
    private List<Item> items;
    private Boolean isLiked;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Item {
    private Long id;
    private String title;
    private Long trackId;
    private String resourceId;
    private Boolean isLiked;

    public Item(PlaylistItem item, Boolean isLiked) {
      this.id = item.getId();
      this.title = item.getTrack().getTitle();
      this.trackId = item.getTrack().getId();
      this.resourceId = item.getTrack().getResourceId();
      this.isLiked = isLiked;
    }

    public Item(Playing playing, Boolean isLiked) {
      this.id = playing.getId();
      this.title = playing.getTrack().getTitle();
      this.trackId = playing.getTrack().getId();
      this.resourceId = playing.getTrack().getResourceId();
      this.isLiked = isLiked;
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class PlaylistWithLike {
    private Long id;
    private String title;
    private Boolean isLiked;
    private List<Item> items = new ArrayList<>();

    public PlaylistWithLike(Playlist playlist, boolean isLiked) {
      this.id = playlist.getId();
      this.title = playlist.getTitle();
      this.isLiked = isLiked;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      PlaylistWithLike that = (PlaylistWithLike) o;
      return Objects.equals(id, that.id) && Objects.equals(title, that.title)
          && Objects.equals(isLiked, that.isLiked);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, title, isLiked);
    }
  }

  @Getter
  @NoArgsConstructor
  public static class PlaylistTrackDto {
    Playlist playlist;
    Track track;
  }

}
