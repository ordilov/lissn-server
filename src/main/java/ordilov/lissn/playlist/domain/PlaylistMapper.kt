package ordilov.lissn.playlist.domain

import org.springframework.stereotype.Component

@Component
class PlaylistMapper {

    fun of(playlist: Playlist): Main {
        return Main(
            id = playlist.id!!,
            title = playlist.title,
            items = playlist.playlistItems.map { of(it) },
            isLiked = false
        )
    }

    fun of(playlistWithLike: PlaylistWithLike): Main {
        return Main(
            id = playlistWithLike.id,
            title = playlistWithLike.title,
            items = playlistWithLike.items,
            isLiked = playlistWithLike.isLiked
        )
    }

    fun of(item: PlaylistItem): Item {
        return Item(
            id = item.id!!,
            trackId = item.track.id,
            resourceId = item.track.resourceId,
            title = item.track.title,
            isLiked = false
        )
    }

    fun of(playlists: List<PlaylistWithLike>): List<Main> {
        return playlists.map { of(it) }
    }
}