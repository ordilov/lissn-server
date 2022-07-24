package ordilov.lissn.like.domain

interface LikeStore {

    fun store(likedTrack: LikedTrack): LikedTrack

    fun store(likedPlaylist: LikedPlaylist): LikedPlaylist

    fun delete(likedTrack: LikedTrack)

    fun delete(likedPlaylist: LikedPlaylist)
}