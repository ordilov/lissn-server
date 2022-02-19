package ordilov.randomplay.playlist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.playlist.interfaces.PlaylistDto.AddPlaylistRequest;
import ordilov.randomplay.playlist.interfaces.PlaylistDto.AddVideoRequest;

@Getter
public class PlaylistCommand {

  @Getter
  @RequiredArgsConstructor
  public static class PlaylistCreateRequest{
    private final Long memberId;
    private final String title;
    public Playlist toEntity(Member member) {
      return Playlist.builder()
          .member(member)
          .title(title)
          .build();
    }
  }




  @Getter
  public static class YoutubeListRequest {

    private final Long memberId;
    private final Long playlistId;
    private final String youtubePlaylistId;

    public YoutubeListRequest(Long id, AddPlaylistRequest request) {
      this.memberId = id;
      this.playlistId = request.getPlaylistId();
      this.youtubePlaylistId = request.getYoutubePlaylistId();
    }
  }

  @Getter
  public static class YoutubeVideoRequest {

    private final Long memberId;
    private final Long playlistId;
    private final String url;

    public YoutubeVideoRequest(Long id,
        Long playlistId,
        AddVideoRequest request) {
      this.memberId = id;
      this.playlistId = playlistId;
      this.url = request.getUrl();
    }
  }

  @Getter
  @RequiredArgsConstructor
  public static class PlaylistUpdateRequest {

    private final Long memberId;
    private final Long playlistId;
    private final String title;

  }

  @Getter
  @RequiredArgsConstructor
  public static class PlaylistDeleteRequest {

    private final Long memberId;
    private final Long playlistId;

  }

  @Getter
  @RequiredArgsConstructor
  public static class PlaylistItemDeleteRequest {

    private final Long memberId;
    private final Long playlistId;
    private final Long playlistItemId;
  }
}
