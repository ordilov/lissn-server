package ordilov.lissn.playlist.domain;

import ordilov.lissn.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.lissn.playlist.domain.youtube.YoutubeVideo;

public interface YoutubeApi {
  YoutubePlaylistItems getPlaylistItems(String playlistId, String accessToken);

  YoutubeVideo getYoutubeVideo(String url, String accessToken);
}
