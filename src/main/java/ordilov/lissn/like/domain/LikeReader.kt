package ordilov.lissn.like.domain

import ordilov.lissn.like.domain.LikedPlaylist
import ordilov.lissn.like.domain.LikedTrack
import ordilov.lissn.member.domain.Member
import ordilov.lissn.playlist.domain.Playlist
import ordilov.lissn.track.domain.Track
import java.util.*

interface LikeReader {

    fun getLikedTrack(member: Member, track: Track): Optional<LikedTrack>

    fun getLikedPlaylist(member: Member, playlist: Playlist): Optional<LikedPlaylist>
}