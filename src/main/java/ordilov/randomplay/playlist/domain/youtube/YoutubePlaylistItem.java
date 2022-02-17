package ordilov.randomplay.playlist.domain.youtube;

import lombok.Getter;
import ordilov.randomplay.playlist.domain.youtube.Snippet.PlaylistItem;

@Getter
public class YoutubePlaylistItem {
  private String id;
  private String kind;
  private String etag;
  private Status status;
  private PlaylistItem snippet;
  private ContentDetails contentDetails;

  @Getter
  private static class ContentDetails{
    private String note;
    private String endAt;
    private String videoId;
    private String startAt;
  }

  @Getter
  private static class Status{
    private String privacyStatus;
  }
}
