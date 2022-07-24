package ordilov.lissn.playlist.adapter.out.persistence;

import ordilov.lissn.playlist.application.port.out.PlaylistStore
import ordilov.lissn.playlist.domain.Playlist
import ordilov.lissn.playlist.domain.PlaylistItem
import org.springframework.stereotype.Component

@Component
class PlaylistStoreImpl(
    val playlistRepository: PlaylistRepository,
    val playlistItemRepository: PlaylistItemRepository
) : PlaylistStore {

    override fun store(playlist: Playlist): Playlist {
        return playlistRepository.save(playlist)
    }

    override fun update(playlistId: Long, title: String) {
        playlistRepository.findById(playlistId).ifPresent { it.updateTitle(title) }
    }

    override fun storeItem(playlist: Playlist, playlistItem: PlaylistItem) {
        TODO("Not yet implemented")
    }

    override fun delete(playlistId: Long) {
        playlistRepository.deleteById(playlistId)
    }

    override fun deleteItem(playlistItemId: Long) {
        playlistItemRepository.deleteById(playlistItemId)
    }
}