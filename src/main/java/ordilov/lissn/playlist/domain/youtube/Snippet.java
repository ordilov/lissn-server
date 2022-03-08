package ordilov.lissn.playlist.domain.youtube;

import java.util.List;
import lombok.Getter;

@Getter
public class Snippet {

  @Getter
  public static class PlaylistItem {

    private String title;
    private String description;
    private String publishedAt;
    private Thumbnails thumbnails;
    private String channelTitle;
    private String channelId;
    private String playlistId;
    private String position;
    private ResourceId resourceId;
    private String videoOwnerChannelTitle;
    private String videoOwnerChannelId;

    @Getter
    public static class ResourceId {

      private String videoId;
      private String kind;
    }
  }

  @Getter
  public static class Video {

    private String title;
    private String channelId;
    private String categoryId;
    private List<String> tags;
    private String publishedAt;
    private String description;
    private String channelTitle;
    private Thumbnails thumbnails;
    private String liveBroadcastContent;
    private String defaultAudioLanguage;
    private Localized localized;
  }

  @Getter
  private static class Localized {
    private String title;
    private String description;
  }
}
