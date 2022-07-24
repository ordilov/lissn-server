package ordilov.lissn.playlist.application.port.out

import ordilov.lissn.playlist.domain.Playlist;
import ordilov.lissn.playlist.domain.PlaylistItem;

interface PlaylistStore {

    fun store(playlist: Playlist): Playlist
    fun update(playlistId: Long, title: String)
    fun storeItem(playlist: Playlist, playlistItem: PlaylistItem)
    fun delete(playlistId: Long)
    fun deleteItem(playlistItemId: Long)
}