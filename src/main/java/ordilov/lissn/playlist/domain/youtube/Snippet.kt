package ordilov.lissn.playlist.domain.youtube

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class PlaylistItem(
    val title: String,
    val description: String,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val channelId: String,
    val playlistId: String,
    val position: String,
    val resourceId: ResourceId,
    val videoOwnerChannelTitle: String,
    val videoOwnerChannelId: String,
)

data class ResourceId(
    val videoId: String,
    val kind: String,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Video(
    val title: String,
    val channelId: String,
    val categoryId: String,
    val publishedAt: String,
    val description: String,
    val channelTitle: String,
    val thumbnails: Thumbnails,
    val liveBroadcastContent: String,
    val defaultAudioLanguage: String,
    val localized: Localized,
    val tags: List<String>
)

data class Localized(
    val title: String,
    val description: String
)