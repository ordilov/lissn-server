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
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistUpdateRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeListRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeVideoRequest;
import ordilov.randomplay.playlist.domain.PlaylistInfo;
import ordilov.randomplay.playlist.domain.PlaylistService;
import ordilov.randomplay.playlist.domain.YoutubeApi;
import ordilov.randomplay.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.randomplay.playlist.domain.youtube.YoutubeVideo;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaylistFacade {

  private final GoogleApi googleApi;
  private final YoutubeApi youtubeApi;
  private final MemberReader memberReader;
  private final PlaylistService playlistService;

  public PlaylistInfo.Main createPlaylist(PlaylistCommand playlistCommand) {
    return playlistService.createPlaylist(playlistCommand);
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
