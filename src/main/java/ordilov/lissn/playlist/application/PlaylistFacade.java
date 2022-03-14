package ordilov.lissn.playlist.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.common.adapter.GoogleApi;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.member.application.port.out.MemberReader;
import ordilov.lissn.playlist.domain.PlaylistCommand.PlaylistCreateRequest;
import ordilov.lissn.playlist.domain.PlaylistCommand.PlaylistDeleteRequest;
import ordilov.lissn.playlist.domain.PlaylistCommand.PlaylistItemDeleteRequest;
import ordilov.lissn.playlist.domain.PlaylistCommand.PlaylistUpdateRequest;
import ordilov.lissn.playlist.domain.PlaylistCommand.YoutubeListRequest;
import ordilov.lissn.playlist.domain.PlaylistCommand.YoutubeVideoRequest;
import ordilov.lissn.playlist.domain.PlaylistInfo;
import ordilov.lissn.playlist.domain.PlaylistService;
import ordilov.lissn.playlist.domain.YoutubeApi;
import ordilov.lissn.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.lissn.playlist.domain.youtube.YoutubeVideo;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaylistFacade {

  private final GoogleApi googleApi;
  private final YoutubeApi youtubeApi;
  private final MemberReader memberReader;
  private final PlaylistService playlistService;

  public PlaylistInfo.Main createPlaylist(PlaylistCreateRequest playlistCommand) {
    return playlistService.createPlaylist(playlistCommand);
  }

  public PlaylistInfo.Main getRandomPlaylist(Long memberId) {
    return playlistService.getRandomPlaylist(memberId);
  }

  public List<PlaylistInfo.Main> getMyPlaylists(Long memberId) {
    return playlistService.getMyPlaylists(memberId);
  }

  public PlaylistInfo.Main addPlaylistItems(YoutubeListRequest command) {
    String accessToken = getAccessToken(command.getMemberId());
    YoutubePlaylistItems youtubePlaylistItems = youtubeApi.getPlaylistItems(
        command.getYoutubePlaylistId(), accessToken);
    return playlistService.addPlaylistItems(command, youtubePlaylistItems);
  }

  public void addPlaylistItem(YoutubeVideoRequest command) {
    String accessToken = getAccessToken(command.getMemberId());
    YoutubeVideo youtubeVideo = youtubeApi.getYoutubeVideo(command.getUrl(), accessToken);
    playlistService.addPlaylistItem(command, youtubeVideo);
  }

  public void deletePlaylist(PlaylistDeleteRequest command) {
    playlistService.deletePlaylist(command);
  }

  public void deletePlaylistItem(PlaylistItemDeleteRequest command) {
    playlistService.deletePlaylistItem(command);
  }

  public void updatePlaylist(PlaylistUpdateRequest command) {
    playlistService.updatePlaylistTitle(command);
  }

  private String getAccessToken(Long memberId) {
    Member member = memberReader.getMemberBy(memberId);
    String refreshToken = member.getRefreshToken();
    return googleApi.getAccessToken(refreshToken);
  }

}
