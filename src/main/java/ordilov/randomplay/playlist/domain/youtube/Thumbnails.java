package ordilov.randomplay.playlist.domain.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Thumbnails {

  @JsonProperty("default")
  private Thumbnail defaultThumbnail;

  @JsonProperty("medium")
  private Thumbnail mediumThumbnail;

  @JsonProperty("high")
  private Thumbnail highThumbnail;

  @JsonProperty("standard")
  private Thumbnail standardThumbnail;

  @JsonProperty("maxres")
  private Thumbnail maxresThumbnail;

  @Getter
  private static class Thumbnail {

    String url;
    int width;
    int height;
  }
}
