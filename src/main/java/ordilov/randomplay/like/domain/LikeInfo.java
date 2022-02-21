package ordilov.randomplay.like.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class LikeInfo {

  @Getter
  @RequiredArgsConstructor
  public static class LikedPlaylistInfo{
    private final Long playlistId;
    private final Boolean isLiked;
  }

  @Getter
  @RequiredArgsConstructor
  public static class LikedTrackInfo{
    private final Long trackId;
    private final Boolean isLiked;
  }
}
