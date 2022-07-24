package ordilov.lissn.playlist.domain.youtube

data class YoutubePlaylistItem(
    val id: String,
    val kind: String,
    val etag: String,
    val status: Status,
    val snippet: PlaylistItem,
    val contentDetails: ContentDetails,
) {
    data class ContentDetails(
        val note: String,
        val endAt: String,
        val videoId: String,
        val startAt: String,
    )

    data class Status(
        val privacyStatus: String
    )
}