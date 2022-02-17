package ordilov.randomplay.playlist.domain;

import ordilov.randomplay.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.randomplay.playlist.domain.youtube.YoutubeVideo;

public interface YoutubeApi {
  YoutubePlaylistItems getPlaylistItems(String playlistId, String accessToken);

  YoutubeVideo getYoutubeVideo(String url, String accessToken);
}
