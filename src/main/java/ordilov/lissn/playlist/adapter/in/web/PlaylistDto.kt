data class CreateRequest(
    val title: String
)

data class UpdateRequest(
    val title: String
)

data class AddPlaylistRequest(
    val playlistId: Long,
    val youtubePlaylistId: String
)

data class AddVideoRequest(
    val url: String
)