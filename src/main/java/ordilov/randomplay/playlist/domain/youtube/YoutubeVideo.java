package ordilov.randomplay.playlist.domain.youtube;

import lombok.Getter;

@Getter
public class YoutubeVideo {

  private String id;
  private String kind;
  private String etag;
  private Snippet snippet;
  private ContentDetails contentDetails;
  private Statistics statistics;


  @Getter
  private static class ContentDetails{
    private String note;
    private String endAt;
    private String startAt;
    private String videoId;
  }

  @Getter
  private static class Statistics{
    private Long viewCount;
    private Long likeCount;
    private Long dislikeCount;
    private Long favoriteCount;
    private Long commentCount;
  }
}
