package ordilov.lissn.member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.common.adapter.CommonResponse;
import ordilov.lissn.member.adapter.in.web.PlayingDto.ChangePlayingRequest;
import ordilov.lissn.member.application.port.in.GetPlayingQuery;
import ordilov.lissn.member.application.port.in.PlayCommand;
import ordilov.lissn.member.domain.playing.PlayingInfo;
import ordilov.lissn.security.userinfo.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playing")
public class PlayingController {

  private final PlayCommand playCommand;

  private final GetPlayingQuery getPlayingQuery;

  @GetMapping
  public CommonResponse<PlayingInfo> getPlayingInfo(
      @AuthenticationPrincipal UserPrincipal userPrincipal) {
    var playingInfo = getPlayingQuery.getPlayingInfo(userPrincipal.getId());
    return CommonResponse.success(playingInfo);
  }

  @PostMapping
  public CommonResponse<String> play(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody ChangePlayingRequest request) {
    var playingCommand = request.toCommand(userPrincipal.getId());
    playCommand.changePlaying(playingCommand);
    return CommonResponse.success("playing changed");
  }

  @PostMapping(value = "/{playingId}")
  public CommonResponse<String> savePlaying(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long playingId) {
    playCommand.savePlaying(userPrincipal.getId(), playingId);
    return CommonResponse.success("playing saved");
  }

  @DeleteMapping(value = "/{playingId}")
  public CommonResponse<String> cancelPlaying(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long playingId) {
    playCommand.deletePlaying(userPrincipal.getId(), playingId);
    return CommonResponse.success("playing deleted");
  }
}
