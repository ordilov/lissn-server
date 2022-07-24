package ordilov.lissn.playlist.application.port.out

import ordilov.lissn.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.lissn.playlist.domain.youtube.YoutubeVideo;

interface YoutubeApi {
    fun getPlaylistItems(playlistId: String): YoutubePlaylistItems

    fun getYoutubeVideo(url: String): YoutubeVideo
}