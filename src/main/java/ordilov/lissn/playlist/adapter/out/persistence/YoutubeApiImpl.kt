package ordilov.lissn.playlist.adapter.out.persistence

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.HttpUrl.Builder
import okhttp3.OkHttpClient
import okhttp3.Request
import ordilov.lissn.playlist.application.port.out.YoutubeApi
import ordilov.lissn.playlist.domain.youtube.YoutubePlaylistItems
import ordilov.lissn.playlist.domain.youtube.YoutubeVideo
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

@Component
class YoutubeApiImpl(
    val client: OkHttpClient = OkHttpClient.Builder().build(),
) : YoutubeApi {

    override fun getPlaylistItems(playlistId: String): YoutubePlaylistItems {
        val youtubePlaylistUrl = getDefaultYoutubeUrl()
            .addPathSegment("playlistItems")
            .addQueryParameter("playlistId", playlistId)
            .addQueryParameter("part", "snippet")
            .addQueryParameter("key", "AIzaSyDScCa9Y0dpV32Wbwy3pHhhfmgqgACJBsA")
            .build();

//    Request request = getDefaultYoutubeRequest(accessToken)
        val request = Request.Builder()
            .url(youtubePlaylistUrl)
            .get()
            .build()

        return requestHelper(request, YoutubePlaylistItems::class.java)
    }


    override fun getYoutubeVideo(url: String): YoutubeVideo {
        val uri = UriComponentsBuilder.fromHttpUrl(url).build()
        val videoId = uri.queryParams.getFirst("v")

        val youtubePlaylistUrl = getDefaultYoutubeUrl()
            .addPathSegment("videos")
            .addQueryParameter("id", videoId)
            .addQueryParameter("part", "snippet")
            .addQueryParameter("key", "AIzaSyDScCa9Y0dpV32Wbwy3pHhhfmgqgACJBsA")
            .build();

        val request = Request.Builder()
            .url(youtubePlaylistUrl)
            .get()
            .build();

        return requestHelper(request, YoutubeVideo::class.java)
    }

    private fun <T> requestHelper(request: Request, objectClass: Class<T>): T {
        val response = client.newCall(request).execute()
        return ObjectMapper().readValue(
            response.body?.string(), objectClass
        )
    }

    private fun getDefaultYoutubeRequest(accessToken: String): Request.Builder {
        return Request.Builder()
            .addHeader("Authorization", "Bearer $accessToken")
    }

    private fun getDefaultYoutubeUrl(): Builder {
        return Builder()
            .scheme("https")
            .host("www.googleapis.com")
            .addPathSegment("youtube")
            .addPathSegment("v3")
    }
}