package ordilov.lissn.playlist.domain.youtube;

import java.util.List;
import lombok.Getter;

@Getter
public class YoutubeVideo {

  private String kind;
  private String etag;
  private List<Item> items;
  private PageInfo pageInfo;

  @Getter
  public static class ContentDetails{
    private String note;
    private String endAt;
    private String startAt;
    private String videoId;
  }

  @Getter
  public static class Statistics{
    private Long viewCount;
    private Long likeCount;
    private Long dislikeCount;
    private Long favoriteCount;
    private Long commentCount;
  }

  @Getter
  public static class Item{
    private String id;
    private String kind;
    private String etag;
    private Snippet.Video snippet;
    private ContentDetails contentDetails;
    private Statistics statistics;
  }
}
