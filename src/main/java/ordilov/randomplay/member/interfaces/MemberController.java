package ordilov.randomplay.member.interfaces;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.common.interfaces.CommonResponse;
import ordilov.randomplay.member.application.MemberFacade;
import ordilov.randomplay.member.domain.MemberInfo;
import ordilov.randomplay.member.domain.playing.PlayingInfo;
import ordilov.randomplay.member.interfaces.MemberDto.ChangePlayingRequest;
import ordilov.randomplay.security.userinfo.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberFacade memberFacade;

  @GetMapping(value = "/members/{memberId}")
  public CommonResponse<MemberInfo> findMember(@PathVariable Long memberId) {
    var memberInfo = memberFacade.getMember(memberId);
    return CommonResponse.success(memberInfo);
  }

  @GetMapping(value = "/members/me")
  @PreAuthorize("hasRole('USER')")
  public CommonResponse<MemberInfo> getCurrentMember(
      @AuthenticationPrincipal UserPrincipal userPrincipal) {
    var memberInfo = memberFacade.getMember(userPrincipal.getId());
    return CommonResponse.success(memberInfo);
  }

  @GetMapping(value = "/playing")
  public CommonResponse<PlayingInfo> getPlayingInfo(
      @AuthenticationPrincipal UserPrincipal userPrincipal) {
    var playingInfo = memberFacade.getPlayingInfo(userPrincipal.getId());
    return CommonResponse.success(playingInfo);
  }

  @PostMapping(value = "/playing")
  public CommonResponse<PlayingInfo> changePlaying(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody ChangePlayingRequest request) {
    var playingCommand = request.toCommand(userPrincipal.getId());
    var playingInfo = memberFacade.changePlaying(playingCommand);
    return CommonResponse.success(playingInfo);
  }



  @DeleteMapping(value = "/playing/{playingId}")
  public CommonResponse<String> cancelPlaying(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @PathVariable Long playingId) {
    memberFacade.deletePlaying(userPrincipal.getId(), playingId);
    return CommonResponse.success("playing deleted");
  }
}
