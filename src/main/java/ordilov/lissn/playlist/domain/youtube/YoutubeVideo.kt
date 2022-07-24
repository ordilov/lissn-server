package ordilov.lissn.playlist.domain.youtube

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class YoutubeVideo(

    val kind: String,
    val etag: String,
    val items: List<Item>,
    val pageInfo: PageInfo,
) {
    data class ContentDetails(
        val note: String,
        val endAt: String,
        val startAt: String,
        val videoId: String,
    )

    data class Statistics(
        val viewCount: Long,
        val likeCount: Long,
        val dislikeCount: Long,
        val favoriteCount: Long,
        val commentCount: Long,
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Item(
        val id: String,
        val kind: String,
        val etag: String,
        val snippet: Video,
        val contentDetails: ContentDetails,
        val statistics: Statistics,
    )
}