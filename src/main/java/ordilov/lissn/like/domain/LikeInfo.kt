package ordilov.lissn.like.domain

data class LikedPlaylistInfo(
    val playlistId: Long,
    val isLiked: Boolean
)

data class LikedTrackInfo(
    val trackId: Long,
    val isLiked: Boolean
)