package ordilov.randomplay.playlist.domain;

public interface PlaylistStore {

  Playlist store(Playlist playlist);

  void storeItem(Playlist playlist, PlaylistItem playlistItem);

  void delete(Long playlistId);

  void deleteItem(Long playlistItemId);
}
