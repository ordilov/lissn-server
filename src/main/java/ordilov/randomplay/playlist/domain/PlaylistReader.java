package ordilov.randomplay.playlist.domain;

import java.util.List;
import ordilov.randomplay.playlist.domain.PlaylistInfo.PlaylistWithLike;

public interface PlaylistReader {

  List<Playlist> getPlaylists();

  Playlist getPlaylistBy(Long id);

  PlaylistWithLike getPlaylistWithLikeBy(Long Id, Long memberId);

  List<Playlist> getPlaylistByMember(Long memberId);

  List<Playlist> getPlaylistByMembers(Long memberId);

  List<PlaylistItem> getPlaylistItemsByMembers(Long memberId);
}
