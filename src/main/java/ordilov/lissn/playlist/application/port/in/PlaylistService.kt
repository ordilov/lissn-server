package ordilov.lissn.playlist.application.port.`in`

import ordilov.lissn.playlist.domain.Main
import ordilov.lissn.playlist.domain.youtube.YoutubePlaylistItems
import ordilov.lissn.playlist.domain.youtube.YoutubeVideo

interface PlaylistService {

    fun addPlaylistItem(
        command: YoutubeVideoRequest,
        youtubeVideo: YoutubeVideo
    )

    fun addPlaylistItems(
        command: YoutubeListRequest,
        youtubePlaylistItems: YoutubePlaylistItems
    ): Main

    fun createPlaylist(command: PlaylistCreateRequest): Main

    fun updatePlaylistTitle(command: PlaylistUpdateRequest)

    fun deletePlaylist(command: PlaylistDeleteRequest)

    fun deletePlaylistItem(command: PlaylistItemDeleteRequest)
}