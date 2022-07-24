package ordilov.lissn.playlist.application.port.out

import ordilov.lissn.playlist.domain.Info
import ordilov.lissn.playlist.domain.Main
import ordilov.lissn.playlist.domain.Playlist
import ordilov.lissn.playlist.domain.PlaylistWithLike

interface PlaylistReader {

    fun getPlaylists(): List<Playlist>

    fun getPlaylistInfo(id: Long, memberId: Long): Info

    fun getPlaylistBy(id: Long): Playlist

    fun getRandomPlaylist(memberId: Long): PlaylistWithLike

    fun getPlaylistByMember(memberId: Long): List<Main>
}