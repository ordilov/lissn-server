package ordilov.randomplay.playlist.domain.youtube;

import java.util.List;
import lombok.Getter;

@Getter
public class YoutubePlaylistItems {
  private String kind;
  private String etag;
  private PageInfo pageInfo;
  private String nextPageToken;
  private String prevPageToken;
  private List<YoutubePlaylistItem> items;

  @Getter
  private static class ContentDetails{
    private String note;
    private String endAt;
    private String startAt;
    private String videoId;
  }

  @Getter
  private static class Status{
    private String privacyStatus;
  }
}
