package ordilov.lissn.playlist.domain;

import java.util.List;
import ordilov.lissn.playlist.domain.PlaylistCommand.PlaylistDeleteRequest;
import ordilov.lissn.playlist.domain.PlaylistCommand.PlaylistItemDeleteRequest;
import ordilov.lissn.playlist.domain.PlaylistCommand.PlaylistUpdateRequest;
import ordilov.lissn.playlist.domain.PlaylistCommand.YoutubeListRequest;
import ordilov.lissn.playlist.domain.PlaylistCommand.YoutubeVideoRequest;
import ordilov.lissn.playlist.domain.PlaylistInfo.Main;
import ordilov.lissn.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.lissn.playlist.domain.youtube.YoutubeVideo;

public interface PlaylistService {

  void addPlaylistItem(YoutubeVideoRequest command, YoutubeVideo youtubeVideo);

  Main addPlaylistItems(YoutubeListRequest command,
      YoutubePlaylistItems youtubePlaylistItems);

  Main createPlaylist(PlaylistCommand.PlaylistCreateRequest command);

  Main getRandomPlaylist(Long id);

  List<Main> getMyPlaylists(Long memberId);

  void updatePlaylistTitle(PlaylistUpdateRequest command);

  void deletePlaylist(PlaylistDeleteRequest command);

  void deletePlaylistItem(PlaylistItemDeleteRequest command);

}
