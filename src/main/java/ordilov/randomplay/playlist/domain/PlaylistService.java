package ordilov.randomplay.playlist.domain;

import java.util.List;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistItemDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistUpdateRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeListRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeVideoRequest;
import ordilov.randomplay.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.randomplay.playlist.domain.youtube.YoutubeVideo;

public interface PlaylistService {

  void addPlaylistItem(YoutubeVideoRequest command, YoutubeVideo youtubeVideo);

  PlaylistInfo.Main addPlaylistItems(YoutubeListRequest command,
      YoutubePlaylistItems youtubePlaylistItems);

  PlaylistInfo.Main createPlaylist(PlaylistCommand command);

  PlaylistInfo.Main getPlaylist(Long id);

  List<PlaylistInfo.Main> getPlaylists();

  List<PlaylistInfo.Main> getMyPlaylists(Long memberId);

  void updatePlaylistTitle(PlaylistUpdateRequest command);

  void deletePlaylist(PlaylistDeleteRequest command);

  void deletePlaylistItem(PlaylistItemDeleteRequest command);
}
