package ordilov.lissn.like.interfaces;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.common.adapter.CommonResponse;
import ordilov.lissn.like.application.LikeFacade;
import ordilov.lissn.like.domain.LikeInfo.LikedPlaylistInfo;
import ordilov.lissn.like.domain.LikeInfo.LikedTrackInfo;
import ordilov.lissn.security.userinfo.UserPrincipal;
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

  private final LikeDtoMapper mapper;
  private final LikeFacade likeFacade;

  @PostMapping("/playlist")
  public CommonResponse<LikedPlaylistInfo> likePlaylist(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody LikeDto.LikePlaylistRequest request) {
    return CommonResponse.success(likeFacade.like(mapper.of(userPrincipal.getId(), request)));
  }

  @PostMapping("/track")
  public CommonResponse<LikedTrackInfo> likeTrack(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody LikeDto.LikeTrackRequest request) {
    return CommonResponse.success(likeFacade.like(mapper.of(userPrincipal.getId(), request)));
  }

}
