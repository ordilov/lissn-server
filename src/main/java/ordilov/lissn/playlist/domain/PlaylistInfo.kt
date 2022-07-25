package ordilov.lissn.playlist.domain

import ordilov.lissn.member.domain.playing.Playing
import ordilov.lissn.track.domain.Track
import java.lang.reflect.Constructor

data class Main(
    val id: Long,
    val title: String,
    val items: List<Item>,
    val isLiked: Boolean
) {
}

data class Info(
    val id: Long,
    val title: String,
    val author: String,
    val authorId: Long,
    val isLiked: Boolean,
    var items: List<Item>
) {
}

data class Item(
    val id: Long,
    val title: String,
    val trackId: Long,
    val resourceId: String,
    val isLiked: Boolean
) {

    constructor(item: PlaylistItem, isLiked: Boolean) : this(
        item.id!!,
        item.track.title,
        item.track.id,
        item.track.resourceId,
        isLiked
    ) {

    }

    constructor(playing: Playing, isLiked: Boolean) : this(
        playing.id,
        playing.track.title,
        playing.track.id,
        playing.track.resourceId,
        isLiked
    )
}

data class PlaylistWithLike(
    val id: Long?,
    val title: String,
    val isLiked: Boolean,
    val items: List<Item>
)
data class PlaylistTrackDto(
    val playlist: Playlist,
    val track: Track
)