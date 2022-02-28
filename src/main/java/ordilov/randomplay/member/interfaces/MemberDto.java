package ordilov.randomplay.member.interfaces;

import lombok.Builder;
import ordilov.randomplay.member.domain.playing.PlayingCommand;

public class MemberDto {

  @Builder
  public static class ChangePlayingRequest {

    private Long trackId;
    private Long playlistId;
    private Long playlistItemId;

    public PlayingCommand.PlayingPlaylist toCommand(Long memberId) {
      return PlayingCommand.PlayingPlaylist.builder()
          .memberId(memberId)
          .playlistId(playlistId)
          .playlistItemId(playlistItemId)
          .build();
    }
  }

}
