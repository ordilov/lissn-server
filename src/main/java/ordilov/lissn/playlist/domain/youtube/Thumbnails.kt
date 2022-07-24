package ordilov.lissn.playlist.domain.youtube

import com.fasterxml.jackson.annotation.JsonProperty

data class Thumbnails(
    @JsonProperty("default")
    val defaultThumbnail: Thumbnail,

    @JsonProperty("medium")
    val mediumThumbnail: Thumbnail,

    @JsonProperty("high")
    val highThumbnail: Thumbnail,

    @JsonProperty("standard")
    val standardThumbnail: Thumbnail,

    @JsonProperty("maxres")
    val maxresThumbnail: Thumbnail
)

data class Thumbnail(
    val url: String,
    val width: Int,
    val height: Int
)