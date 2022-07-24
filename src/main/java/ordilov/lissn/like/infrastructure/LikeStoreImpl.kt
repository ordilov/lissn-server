package ordilov.lissn.like.infrastructure

import ordilov.lissn.like.domain.LikeStore
import ordilov.lissn.like.domain.LikedPlaylist
import ordilov.lissn.like.domain.LikedTrack
import org.springframework.stereotype.Repository

@Repository
class LikeStoreImpl(
    val likedTrackRepository: LikedTrackRepository,
    val likedPlaylistRepository: LikedPlaylistRepository
) : LikeStore {

    override fun store(likedTrack: LikedTrack): LikedTrack {
        return likedTrackRepository.save(likedTrack)
    }

    override fun store(likedPlaylist: LikedPlaylist): LikedPlaylist {
        return likedPlaylistRepository.save(likedPlaylist)
    }

    override fun delete(likedTrack: LikedTrack) {
        likedTrack.track.removeLikeCount()
        likedTrackRepository.delete(likedTrack)
    }

    override fun delete(likedPlaylist: LikedPlaylist) {
        likedPlaylist.playlist?.decreaseLikeCount()
        likedPlaylistRepository.delete(likedPlaylist)
    }
}