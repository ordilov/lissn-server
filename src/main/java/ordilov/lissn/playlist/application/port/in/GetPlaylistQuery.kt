package ordilov.lissn.playlist.application.port.`in`

import ordilov.lissn.playlist.domain.Info
import ordilov.lissn.playlist.domain.Main

interface GetPlaylistQuery {

    fun getRandomPlaylist(id: Long): Main

    fun getMyPlaylists(memberId: Long): List<Main>

    fun getPlaylist(id: Long, memberId: Long): Info
}