package ordilov.randomplay.playlist.domain;

import java.util.List;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistItemDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistUpdateRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeListRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeVideoRequest;
import ordilov.randomplay.playlist.domain.PlaylistInfo.Main;
import ordilov.randomplay.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.randomplay.playlist.domain.youtube.YoutubeVideo;

public interface PlaylistService {

  void addPlaylistItem(YoutubeVideoRequest command, YoutubeVideo youtubeVideo);

  Main addPlaylistItems(YoutubeListRequest command,
      YoutubePlaylistItems youtubePlaylistItems);

  Main createPlaylist(PlaylistCommand.PlaylistCreateRequest command);

  Main getPlaylistWithLike(Long id);

  List<Main> getPlaylists();

  List<Main> getMyPlaylists(Long memberId);

  void updatePlaylistTitle(PlaylistUpdateRequest command);

  void deletePlaylist(PlaylistDeleteRequest command);

  void deletePlaylistItem(PlaylistItemDeleteRequest command);

  Main getRandomPlaylist();
}
