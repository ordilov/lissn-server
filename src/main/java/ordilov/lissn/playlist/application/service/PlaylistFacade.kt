package ordilov.lissn.playlist.application.service

import ordilov.lissn.member.application.port.out.MemberReader
import ordilov.lissn.common.adapter.GoogleApi
import ordilov.lissn.playlist.application.port.`in`.*
import ordilov.lissn.playlist.application.port.out.YoutubeApi
import ordilov.lissn.playlist.domain.Main
import org.springframework.stereotype.Service

@Service
class PlaylistFacade(
    val googleApi: GoogleApi,
    val youtubeApi: YoutubeApi,
    val memberReader: MemberReader,
    val playlistService: PlaylistService,
) {

    fun createPlaylist(playlistCommand: PlaylistCreateRequest): Main {
        return playlistService.createPlaylist(playlistCommand)
    }

    fun addPlaylistItems(command: YoutubeListRequest): Main {
        val youtubePlaylistItems = youtubeApi.getPlaylistItems(
            command.youtubePlaylistId
        )
        return playlistService.addPlaylistItems(command, youtubePlaylistItems)
    }

    fun addPlaylistItem(command: YoutubeVideoRequest) {
//    String accessToken = getAccessToken(command.getMemberId());
        val youtubeVideo = youtubeApi.getYoutubeVideo(command.url)
        playlistService.addPlaylistItem(command, youtubeVideo)
    }

    fun deletePlaylist(command: PlaylistDeleteRequest) {
        playlistService.deletePlaylist(command)
    }

    fun deletePlaylistItem(command: PlaylistItemDeleteRequest) {
        playlistService.deletePlaylistItem(command)
    }

    fun updatePlaylist(command: PlaylistUpdateRequest) {
        playlistService.updatePlaylistTitle(command)
    }

}