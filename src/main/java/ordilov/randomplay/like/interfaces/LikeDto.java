package ordilov.randomplay.like.interfaces;

import lombok.Getter;

public class LikeDto {

  @Getter
  public static class LikeTrackRequest {

    private Long trackId;
  }

  @Getter
  public static class LikePlaylistRequest {

    private Long playlistId;
  }
}
