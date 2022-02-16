package ordilov.randomplay.playlist.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class PlaylistInfo {

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Main {
    private Long id;
    private String title;
    private List<PlaylistItem> playlistItems;
  }

  @Getter
  @Builder
  public static class PlaylistItem{
    private final Long id;
    private final String title;
    private final String resourceId;
  }
}
