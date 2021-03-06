package ordilov.lissn.playlist.infrastructure;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.playlist.domain.Playlist;
import ordilov.lissn.playlist.domain.PlaylistItem;
import ordilov.lissn.playlist.domain.PlaylistStore;
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
  public void update(Long playlistId, String title) {
    playlistRepository.findById(playlistId).ifPresent(playlist -> playlist.updateTitle(title));
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
