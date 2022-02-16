package ordilov.randomplay.playlist.domain.youtube;

import lombok.Getter;

@Getter
public class YoutubePlaylistItem {
  private String id;
  private String kind;
  private String etag;
  private Status status;
  private Snippet snippet;
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
