package ordilov.randomplay.like.interfaces;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.common.interfaces.CommonResponse;
import ordilov.randomplay.like.application.LikeFacade;
import ordilov.randomplay.security.userinfo.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

  private final LikeFacade likeFacade;
  private final LikeDtoMapper mapper;

  @PostMapping("/playlist")
  public CommonResponse<String> likePlaylist(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody LikeDto.LikePlaylistRequest request) {
    likeFacade.like(mapper.of(userPrincipal.getId(), request));
    return CommonResponse.success("like");
  }

  @PostMapping("/track")
  public CommonResponse<String> likeTrack(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody LikeDto.LikeTrackRequest request) {

    log.info("ID:  {}", userPrincipal.getId());
    likeFacade.like(mapper.of(userPrincipal.getId(), request));
    return CommonResponse.success("like");
  }


}
