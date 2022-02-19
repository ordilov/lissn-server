package ordilov.randomplay.like.application;

import lombok.RequiredArgsConstructor;
import ordilov.randomplay.like.domain.LikeCommand;
import ordilov.randomplay.like.domain.LikeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeFacade {

  private final LikeService likeService;

  public void like(LikeCommand.LikePlaylistRequest request) {
    likeService.like(request);
  }

  public void like(LikeCommand.LikeTrackRequest request) {
    likeService.like(request);
  }
}
