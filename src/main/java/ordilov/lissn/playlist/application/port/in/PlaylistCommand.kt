package ordilov.lissn.playlist.application.port.`in`

import AddPlaylistRequest
import AddVideoRequest

data class PlaylistCreateRequest(
    val memberId: Long,
    val title: String
)

data class YoutubeListRequest(
    val memberId: Long,
    val playlistId: Long,
    val youtubePlaylistId: String,
) {

    constructor(id: Long, request: AddPlaylistRequest) : this(
        memberId = id,
        playlistId = request.playlistId,
        youtubePlaylistId = request.youtubePlaylistId
    )
}

data class YoutubeVideoRequest(
    val memberId: Long,
    val playlistId: Long,
    val url: String
) {

    constructor (
        id: Long,
        playlistId: Long,
        request: AddVideoRequest
    ) : this(
        memberId = id,
        playlistId = playlistId,
        url = request.url
    )
}

data class PlaylistUpdateRequest(
    val memberId: Long,
    val playlistId: Long,
    val title: String
)

data class PlaylistDeleteRequest(
    val memberId: Long,
    val playlistId: Long
)

data class PlaylistItemDeleteRequest(
    val memberId: Long,
    val playlistId: Long,
    val playlistItemId: Long
)