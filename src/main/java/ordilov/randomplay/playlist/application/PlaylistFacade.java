package ordilov.randomplay.playlist.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.common.interfaces.GoogleApi;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberReader;
import ordilov.randomplay.playlist.domain.PlaylistCommand;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistItemDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeListRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeVideoRequest;
import ordilov.randomplay.playlist.domain.PlaylistInfo;
import ordilov.randomplay.playlist.domain.PlaylistService;
import ordilov.randomplay.playlist.domain.YoutubeApi;
import ordilov.randomplay.playlist.domain.youtube.YoutubePlaylistItems;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaylistFacade {

  private final MemberReader memberReader;
  private final PlaylistService playlistService;
  private final GoogleApi googleApi;
  private final YoutubeApi youtubeApi;

  public PlaylistInfo.Main createPlaylist(PlaylistCommand playlistCommand) {
    return playlistService.createPlaylist(playlistCommand);
  }

  public List<PlaylistInfo.Main> getMyPlaylists(Long memberId) {
    return playlistService.getMyPlaylists(memberId);
  }

  public PlaylistInfo.Main addPlaylistItems(YoutubeListRequest command) {
    Member member = memberReader.getMemberBy(command.getMemberId());
    String refreshToken = member.getRefreshToken();
    String accessToken = googleApi.getAccessToken(refreshToken);

    YoutubePlaylistItems youtubePlaylistItems = youtubeApi.getPlaylistItems(
        command.getYoutubePlaylistId(), accessToken);

    return playlistService.addPlaylistItems(command, youtubePlaylistItems);
  }

  public void addPlaylistItem(YoutubeVideoRequest command){
    UriComponents uri = UriComponentsBuilder.fromHttpUrl(command.getYoutubeVideoUrl()).build();
    MultiValueMap<String, String> queryParams = uri.getQueryParams();
    String videoId = queryParams.getFirst("v");
    playlistService.addPlaylistItem(command, videoId);
  }

  public void deletePlaylist(PlaylistDeleteRequest command) {
    playlistService.deletePlaylist(command);
  }

  public void deletePlaylistItem(PlaylistItemDeleteRequest command) {
    playlistService.deletePlaylistItem(command);
  }
}
