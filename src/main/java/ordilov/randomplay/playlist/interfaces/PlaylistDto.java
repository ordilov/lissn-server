package ordilov.randomplay.playlist.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class PlaylistDto {

  @Getter
  @Setter
  @ToString
  public static class CreateRequest {
    private String title;
  }

  @Getter
  @Setter
  @ToString
  public static class UpdateRequest {
    private String title;
  }

  @Getter
  @Setter
  public static class AddPlaylistRequest {
    private Long playlistId;
    private String youtubePlaylistId;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AddVideoRequest {
    private String url;
  }
}
