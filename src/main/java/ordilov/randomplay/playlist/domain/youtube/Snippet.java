package ordilov.randomplay.playlist.domain.youtube;

import lombok.Getter;

@Getter
public class Snippet {
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
    public static class ResourceId{
        private String videoId;
        private String kind;
    }
}
