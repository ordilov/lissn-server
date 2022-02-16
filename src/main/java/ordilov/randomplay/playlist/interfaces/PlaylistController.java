package ordilov.randomplay.playlist.interfaces;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.common.interfaces.CommonResponse;
import ordilov.randomplay.playlist.application.PlaylistFacade;
import ordilov.randomplay.playlist.domain.PlaylistCommand;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistItemDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeListRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeVideoRequest;
import ordilov.randomplay.playlist.domain.PlaylistInfo;
import ordilov.randomplay.security.userinfo.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  public CommonResponse<List<PlaylistInfo.Main>> getPlaylists(
      @AuthenticationPrincipal UserPrincipal userPrincipal) {
    return CommonResponse.success(playlistFacade.getMyPlaylists(userPrincipal.getId()));
  }

  @PostMapping
  public CommonResponse<PlaylistInfo.Main> createPlaylist(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody PlaylistDto.CreateRequest request) {
    var command = new PlaylistCommand(userPrincipal.getId(), request.getTitle());
    return CommonResponse.success(playlistFacade.createPlaylist(command));
  }

  @PostMapping("/youtube")
  public CommonResponse<PlaylistInfo.Main> createPlaylistFromYoutube(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody PlaylistDto.CreateYoutubeRequest request) {
    var command = new YoutubeListRequest(userPrincipal.getId(), request);
    return CommonResponse.success(playlistFacade.addPlaylistItems(command));
  }

  @DeleteMapping("/{playlistId}")
  public CommonResponse deletePlaylist(
      @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long playlistId) {
    log.info("playlistId: {}", playlistId);
    var command = new PlaylistDeleteRequest(userPrincipal.getId(), playlistId);
    playlistFacade.deletePlaylist(command);
    return CommonResponse.success("Playlist deleted");
  }

  @PostMapping("/{playlistId}/playlistItems")
  public CommonResponse<String> addPlaylistItem(
      @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long playlistId,
      @RequestBody PlaylistDto.CreateYoutubeVideoRequest request) {
    var command = new YoutubeVideoRequest(userPrincipal.getId(), playlistId, request);

    return CommonResponse.success("PlaylistItem added");
  }

  @DeleteMapping("/{playlistId}/playlistItems/{playlistItemId}")
  public CommonResponse deletePlaylistItem(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long playlistId,
      @PathVariable Long playlistItemId) {
    log.info("playlistId: {}", playlistId);
    var command = new PlaylistItemDeleteRequest(userPrincipal.getId(), playlistId, playlistItemId);
    playlistFacade.deletePlaylistItem(command);
    return CommonResponse.success("Playlist deleted");
  }
}
