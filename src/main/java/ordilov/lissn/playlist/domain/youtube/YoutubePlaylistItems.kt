package ordilov.lissn.playlist.domain.youtube

data class YoutubePlaylistItems(
    val kind: String,
    val etag: String,
    val pageInfo: PageInfo,
    val nextPageToken: String,
    val prevPageToken: String,
    val items: List<YoutubePlaylistItem>,
)

data class ContentDetails(
    val note: String,
    val endAt: String,
    val startAt: String,
    val videoId: String
)

data class Status(
    val privacyStatus: String
)