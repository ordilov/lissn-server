package ordilov.lissn.like.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class LikeCommand {

  @Getter
  @AllArgsConstructor
  public static class LikePlaylistRequest{
    private Long memberId;
    private Long playlistId;

  }

  @Getter
  @AllArgsConstructor
  public static class LikeTrackRequest{
    private Long memberId;
    private Long trackId;
  }
}
