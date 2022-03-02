package ordilov.randomplay.playlist.domain;

import java.util.List;
import ordilov.randomplay.playlist.domain.PlaylistInfo.PlaylistWithLike;

public interface PlaylistReader {

  List<Playlist> getPlaylists();

  Playlist getPlaylistBy(Long id);

  PlaylistWithLike getRandomPlaylist(Long memberId);

  List<PlaylistInfo.Main> getPlaylistByMember(Long memberId);

}
