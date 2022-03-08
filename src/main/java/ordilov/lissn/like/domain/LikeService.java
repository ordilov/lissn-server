package ordilov.lissn.like.domain;

import ordilov.lissn.like.domain.LikeInfo.LikedPlaylistInfo;
import ordilov.lissn.like.domain.LikeInfo.LikedTrackInfo;

public interface LikeService {

  LikedTrackInfo like(LikeCommand.LikeTrackRequest request);

  LikedPlaylistInfo like(LikeCommand.LikePlaylistRequest request);
}
