package ordilov.lissn.like.infrastructure

import ordilov.lissn.like.domain.LikeReader
import ordilov.lissn.like.domain.LikedPlaylist
import ordilov.lissn.like.domain.LikedTrack
import ordilov.lissn.member.domain.Member
import ordilov.lissn.playlist.domain.Playlist
import ordilov.lissn.track.domain.Track
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class LikeReaderImpl(
    val likedTrackRepository: LikedTrackRepository,
    val likedPlaylistRepository: LikedPlaylistRepository
) : LikeReader {

    override fun getLikedTrack(member: Member, track: Track): Optional<LikedTrack> {
        return likedTrackRepository.findByMemberAndTrack(member, track)
    }

    override fun getLikedPlaylist(member: Member, playlist: Playlist): Optional<LikedPlaylist> {
        return likedPlaylistRepository.findByMemberAndPlaylist(member, playlist)
    }
}