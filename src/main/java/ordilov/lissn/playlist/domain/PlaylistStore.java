package ordilov.lissn.playlist.domain;

public interface PlaylistStore {

  Playlist store(Playlist playlist);

  void update(Long playlistId, String title);

  void storeItem(Playlist playlist, PlaylistItem playlistItem);

  void delete(Long playlistId);

  void deleteItem(Long playlistItemId);
}
