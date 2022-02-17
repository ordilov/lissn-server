package ordilov.randomplay.playlist.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ordilov.randomplay.playlist.domain.YoutubeApi;
import ordilov.randomplay.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.randomplay.playlist.domain.youtube.YoutubeVideo;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class YoutubeApiImpl implements YoutubeApi {

  private final OkHttpClient client = new OkHttpClient();

  @Override
  public YoutubePlaylistItems getPlaylistItems(String playlistId, String accessToken) {
    HttpUrl youtubePlaylistUrl = getDefaultYoutubeUrl()
        .addPathSegment("playlistItems")
        .addQueryParameter("playlistId", playlistId)
        .addQueryParameter("part", "snippet")
        .build();

    Request request = getDefaultYoutubeRequest(accessToken)
        .url(youtubePlaylistUrl)
        .get()
        .build();

    return requestHelper(request, YoutubePlaylistItems.class);
  }

  @Override
  public YoutubeVideo getYoutubeVideo(String url, String accessToken) {
    UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
    MultiValueMap<String, String> queryParams = uri.getQueryParams();
    String videoId = queryParams.getFirst("v");

    HttpUrl youtubePlaylistUrl = getDefaultYoutubeUrl()
        .addPathSegment("videos")
        .addQueryParameter("id", videoId)
        .addQueryParameter("part", "snippet")
        .build();

    Request request = getDefaultYoutubeRequest(accessToken)
        .url(youtubePlaylistUrl)
        .get()
        .build();

    return requestHelper(request, YoutubeVideo.class);
  }

  private <T> T requestHelper(Request request, Class<T> objectClass) {
    T result = null;
    try (Response response = client.newCall(request).execute()) {
      result = new ObjectMapper().readValue(
          Objects.requireNonNull(response.body()).string(), objectClass);
    } catch (IOException e) {
      log.error("error", e);
    }
    return result;
  }

  private Request.Builder getDefaultYoutubeRequest(String accessToken) {
    return new Request.Builder()
        .addHeader("Authorization", "Bearer " + accessToken);
  }

  private Builder getDefaultYoutubeUrl() {
    return new Builder()
        .scheme("https")
        .host("www.googleapis.com")
        .addPathSegment("youtube")
        .addPathSegment("v3");
  }
}
