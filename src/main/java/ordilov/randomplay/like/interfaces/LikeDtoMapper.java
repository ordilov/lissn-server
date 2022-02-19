package ordilov.randomplay.like.interfaces;

import ordilov.randomplay.like.domain.LikeCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeDtoMapper {

  LikeCommand.LikeTrackRequest of(Long memberId, LikeDto.LikeTrackRequest likeTrackRequest);

  LikeCommand.LikePlaylistRequest of(Long memberId,
      LikeDto.LikePlaylistRequest likePlaylistRequest);

}
