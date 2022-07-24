package ordilov.lissn.like.domain

interface LikeService {

    fun like(request: LikeTrackCommand): LikedTrackInfo

    fun like(request: LikePlaylistCommand): LikedPlaylistInfo
}