package ordilov.randomplay.like.application;

import lombok.RequiredArgsConstructor;
import ordilov.randomplay.like.domain.LikeCommand;
import ordilov.randomplay.like.domain.LikeInfo.LikedPlaylistInfo;
import ordilov.randomplay.like.domain.LikeInfo.LikedTrackInfo;
import ordilov.randomplay.like.domain.LikeService;
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
