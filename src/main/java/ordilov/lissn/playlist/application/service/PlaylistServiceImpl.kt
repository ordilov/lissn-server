package ordilov.lissn.playlist.application.service;

import ordilov.lissn.member.application.port.out.MemberReader
import ordilov.lissn.playlist.application.port.`in`.*
import ordilov.lissn.playlist.application.port.out.PlaylistReader
import ordilov.lissn.playlist.application.port.out.PlaylistStore
import ordilov.lissn.playlist.domain.Main
import ordilov.lissn.playlist.domain.Playlist
import ordilov.lissn.playlist.domain.PlaylistItem
import ordilov.lissn.playlist.domain.PlaylistMapper
import ordilov.lissn.playlist.domain.youtube.YoutubePlaylistItem
import ordilov.lissn.playlist.domain.youtube.YoutubePlaylistItems
import ordilov.lissn.playlist.domain.youtube.YoutubeVideo
import ordilov.lissn.track.domain.Track
import ordilov.lissn.track.domain.TrackStore
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PlaylistServiceImpl(
    val mapper: PlaylistMapper,
    val trackStore: TrackStore,
    val memberReader: MemberReader,
    val playlistStore: PlaylistStore,
    val playlistReader: PlaylistReader,
) : PlaylistService {

    override fun addPlaylistItem(command: YoutubeVideoRequest, youtubeVideo: YoutubeVideo) {
        val item = youtubeVideo.items.get(0)
        val track = trackStore.store(
            Track(
                title = item.snippet.title,
                resourceId = item.id
            )
        )
        val playlist = playlistReader.getPlaylistBy(command.playlistId)
        val playlistItem = PlaylistItem(playlist, track)
        playlist.addPlaylistItem(playlistItem)
    }

    override fun addPlaylistItems(
        command: YoutubeListRequest,
        youtubePlaylistItems: YoutubePlaylistItems
    ): Main {
        val playlist = playlistReader.getPlaylistBy(command.playlistId)

        val playlistItems = youtubePlaylistItems.items
            .map(YoutubePlaylistItem::snippet)
            .map { snippet ->
                trackStore.store(
                    Track(snippet.title, snippet.resourceId.videoId)
                )
            }
            .map { track -> PlaylistItem(playlist, track) }

        playlist.addPlaylistItems(playlistItems)
        return mapper.of(playlist)
    }

    override fun createPlaylist(command: PlaylistCreateRequest): Main {
        val member = memberReader.getMemberBy(command.memberId)
        val playlist = playlistStore.store(Playlist(command.title, member))
        member.createPlaylist(playlist)
        return mapper.of(playlist)
    }

    override fun updatePlaylistTitle(command: PlaylistUpdateRequest) {
        playlistStore.update(command.playlistId, command.title)
    }

    override fun deletePlaylist(command: PlaylistDeleteRequest) {
        playlistStore.delete(command.playlistId)
    }

    override fun deletePlaylistItem(command: PlaylistItemDeleteRequest) {
        playlistStore.deleteItem(command.playlistItemId)
    }
}