package ordilov.randomplay.playlist.interfaces;

import lombok.Getter;
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
  public static class CreateYoutubeRequest {
    private Long playlistId;
    private String youtubePlaylistId;
  }

  @Getter
  @Setter
  public static class CreateYoutubeVideoRequest {
    private Long playlistId;
    private String youtubeVideoUrl;
  }
}
