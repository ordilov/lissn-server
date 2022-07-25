package ordilov.lissn.playlist.application.service

import ordilov.lissn.member.application.port.out.MemberReader
import ordilov.lissn.playlist.application.port.`in`.GetPlaylistQuery
import ordilov.lissn.playlist.application.port.out.PlaylistReader
import ordilov.lissn.playlist.domain.Info
import ordilov.lissn.playlist.domain.Main
import ordilov.lissn.playlist.domain.PlaylistMapper
import org.springframework.stereotype.Service

@Service
class GetPlaylistService(
    val mapper: PlaylistMapper,
    val playlistReader: PlaylistReader,
    val memberReader: MemberReader,
) : GetPlaylistQuery {

    override fun getRandomPlaylist(id: Long): Main {
        val playlistWithLikeBy = playlistReader.getRandomPlaylist(id)
        return mapper.of(playlistWithLikeBy)
    }

    override fun getMyPlaylists(memberId: Long): List<Main> {
        return playlistReader.getPlaylistByMember(memberId)
    }

    override fun getPlaylist(id: Long, memberId: Long): Info {
        return playlistReader.getPlaylistInfo(id, memberId)
    }
}