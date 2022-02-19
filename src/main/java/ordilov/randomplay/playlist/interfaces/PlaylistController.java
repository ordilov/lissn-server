package ordilov.randomplay.playlist.interfaces;

import static ordilov.randomplay.playlist.interfaces.PlaylistDto.AddPlaylistRequest;
import static ordilov.randomplay.playlist.interfaces.PlaylistDto.AddVideoRequest;
import static ordilov.randomplay.playlist.interfaces.PlaylistDto.CreateRequest;
import static ordilov.randomplay.playlist.interfaces.PlaylistDto.UpdateRequest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.common.interfaces.CommonResponse;
import ordilov.randomplay.playlist.application.PlaylistFacade;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistCreateRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistItemDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistUpdateRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeListRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeVideoRequest;
import ordilov.randomplay.playlist.domain.PlaylistInfo;
import ordilov.randomplay.security.userinfo.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/playlists")
public class PlaylistController {

  private final PlaylistFacade playlistFacade;

  @GetMapping("/random")
  public CommonResponse<PlaylistInfo.Main> getRandomPlaylist(
      @AuthenticationPrincipal UserPrincipal userPrincipal) {

    if(userPrincipal == null) {
      return CommonResponse.success(playlistFacade.getRandomPlaylist(null));
    }

    return CommonResponse.success(playlistFacade.getRandomPlaylist(userPrincipal.getId()));
  }

  @GetMapping
  public CommonResponse<List<PlaylistInfo.Main>> getPlaylists(
      @AuthenticationPrincipal UserPrincipal userPrincipal) {
    return CommonResponse.success(playlistFacade.getMyPlaylists(userPrincipal.getId()));
  }

  @PostMapping
  public CommonResponse<PlaylistInfo.Main> createPlaylist(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody CreateRequest request) {
    var command = new PlaylistCreateRequest(userPrincipal.getId(), request.getTitle());
    return CommonResponse.success(playlistFacade.createPlaylist(command));
  }

  @PostMapping("/youtube")
  public CommonResponse<PlaylistInfo.Main> addYoutubePlaylist(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody AddPlaylistRequest request) {
    var command = new YoutubeListRequest(userPrincipal.getId(), request);
    return CommonResponse.success(playlistFacade.addPlaylistItems(command));
  }

  @PatchMapping("/{playlistId}")
  public CommonResponse<String> updatePlaylist(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long playlistId,
      @RequestBody UpdateRequest request) {
    var command = new PlaylistUpdateRequest(userPrincipal.getId(), playlistId, request.getTitle());
    playlistFacade.updatePlaylist(command);
    return CommonResponse.success("Playlist updated");
  }

  @DeleteMapping("/{playlistId}")
  public CommonResponse<String> deletePlaylist(
      @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long playlistId) {
    var command = new PlaylistDeleteRequest(userPrincipal.getId(), playlistId);
    playlistFacade.deletePlaylist(command);
    return CommonResponse.success("Playlist deleted");
  }

  @PostMapping("/{playlistId}/playlistItems")
  public CommonResponse<String> addPlaylistItem(
      @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long playlistId,
      @RequestBody AddVideoRequest request) {
    var command = new YoutubeVideoRequest(userPrincipal.getId(), playlistId, request);
    playlistFacade.addPlaylistItem(command);
    return CommonResponse.success("PlaylistItem added");
  }

  @DeleteMapping("/{playlistId}/playlistItems/{playlistItemId}")
  public CommonResponse<String> deletePlaylistItem(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long playlistId,
      @PathVariable Long playlistItemId) {
    var command = new PlaylistItemDeleteRequest(userPrincipal.getId(), playlistId, playlistItemId);
    playlistFacade.deletePlaylistItem(command);
    return CommonResponse.success("Playlist deleted");
  }
}
