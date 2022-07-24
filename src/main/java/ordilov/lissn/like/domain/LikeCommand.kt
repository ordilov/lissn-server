package ordilov.lissn.like.domain

data class LikePlaylistCommand(
    val memberId: Long,
    val playlistId: Long
)

data class LikeTrackCommand(
    val memberId: Long,
    val trackId: Long
)