package ordilov.lissn.playlist.domain.youtube

data class YoutubePlaylists(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: String,
    val status: Status,
    val contentDetails: ContentDetails,
    val player: Player,
    val tags: Array<String>,
) {
    data class Status(
        val privacyStatus: String
    )

    data class ContentDetails(
        val itemCount: Int
    )

    data class Player(
        val embedHtml: String
    )

}