data class ChangePlayingRequest(
    val trackId: Long,
    val playlistId: Long,
    val playlistItemId: Long,
) {
}