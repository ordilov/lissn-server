package ordilov.lissn.like.application;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.like.domain.LikeCommand;
import ordilov.lissn.like.domain.LikeInfo.LikedPlaylistInfo;
import ordilov.lissn.like.domain.LikeInfo.LikedTrackInfo;
import ordilov.lissn.like.domain.LikeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeFacade {

  private final LikeService likeService;

  public LikedPlaylistInfo like(LikeCommand.LikePlaylistRequest request) {
    return likeService.like(request);
  }

  public LikedTrackInfo like(LikeCommand.LikeTrackRequest request) {
    return likeService.like(request);
  }
}
