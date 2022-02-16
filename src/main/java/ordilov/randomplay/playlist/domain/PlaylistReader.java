package ordilov.randomplay.playlist.domain;

import java.util.List;

public interface PlaylistReader {

  List<Playlist> getPlaylists();

  Playlist getPlaylistBy(Long id);

  List<Playlist> getPlaylistByMember(Long memberId);

  List<Playlist> getPlaylistByMembers(Long memberId);

  List<PlaylistItem> getPlaylistItemsByMembers(Long memberId);
}
