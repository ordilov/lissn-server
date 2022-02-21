package ordilov.randomplay.like.domain;

import ordilov.randomplay.like.domain.LikeInfo.LikedPlaylistInfo;
import ordilov.randomplay.like.domain.LikeInfo.LikedTrackInfo;

public interface LikeService {

  LikedTrackInfo like(LikeCommand.LikeTrackRequest request);

  LikedPlaylistInfo like(LikeCommand.LikePlaylistRequest request);
}
