package ordilov.lissn.like.application

import ordilov.lissn.like.domain.*
import org.springframework.stereotype.Service

@Service
class LikeFacade(
    val likeService: LikeService
) {

    fun like(command: LikePlaylistCommand): LikedPlaylistInfo {
        return likeService.like(command)
    }

    fun like(command: LikeTrackCommand): LikedTrackInfo {
        return likeService.like(command);
    }
}