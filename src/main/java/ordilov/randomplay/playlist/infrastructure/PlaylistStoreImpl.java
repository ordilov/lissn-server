package ordilov.randomplay.playlist.infrastructure;

import lombok.RequiredArgsConstructor;
import ordilov.randomplay.playlist.domain.Playlist;
import ordilov.randomplay.playlist.domain.PlaylistItem;
import ordilov.randomplay.playlist.domain.PlaylistStore;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaylistStoreImpl implements PlaylistStore {

  private final PlaylistRepository playlistRepository;
  private final PlaylistItemRepository playlistItemRepository;

  @Override
  public Playlist store(Playlist playlist) {
    return playlistRepository.save(playlist);
  }

  @Override
  public void storeItem(Playlist playlist, PlaylistItem playlistItem) {

  }

  @Override
  public void delete(Long playlistId) {
    playlistRepository.deleteById(playlistId);
  }

  @Override
  public void deleteItem(Long playlistItemId) {
    playlistItemRepository.deleteById(playlistItemId);
  }
}
