package ordilov.lissn.member.domain.playing;

import lombok.Builder;
import lombok.Getter;

public class PlayingCommand {

  public static class PlayingTrack{
    private Long memberId;
    private Long trackId;
  }

  @Getter
  @Builder
  public static class PlayingPlaylist{
    private Long memberId;
    private Long playlistId;
    private Long playlistItemId;
  }
}
