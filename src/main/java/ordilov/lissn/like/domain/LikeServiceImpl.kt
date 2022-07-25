package ordilov.lissn.like.domain

import ordilov.lissn.member.application.port.out.MemberReader
import ordilov.lissn.playlist.application.port.out.PlaylistReader
import ordilov.lissn.track.domain.TrackReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LikeServiceImpl(
    val likeStore: LikeStore,
    val likeReader: LikeReader,
    val trackReader: TrackReader,
    val memberReader: MemberReader,
    val playlistReader: PlaylistReader
) : LikeService {

    override fun like(request: LikeTrackCommand): LikedTrackInfo {
        val member = memberReader.getMemberBy(request.memberId)
        val track = trackReader.getTrackBy(request.trackId)
        val likedTrack = likeReader.getLikedTrack(member, track)
        likedTrack.ifPresentOrElse(
            likeStore::delete
        ) { likeStore.store(LikedTrack(member, track)) }

        return LikedTrackInfo(request.trackId, likedTrack.isEmpty)
    }

    override fun like(request: LikePlaylistCommand): LikedPlaylistInfo {
        val member = memberReader.getMemberBy(request.memberId)
        val playlist = playlistReader.getPlaylistBy(request.playlistId)
        val likedPlaylist = likeReader.getLikedPlaylist(member, playlist)
        likedPlaylist.ifPresentOrElse(
            likeStore::delete
        ) { likeStore.store(LikedPlaylist(member, playlist)) }

        return LikedPlaylistInfo(request.playlistId, likedPlaylist.isEmpty)
    }

}