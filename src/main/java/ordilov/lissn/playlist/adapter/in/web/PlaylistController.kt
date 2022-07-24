package ordilov.lissn.playlist.adapter.`in`.web

import AddPlaylistRequest
import AddVideoRequest
import CreateRequest
import UpdateRequest
import ordilov.lissn.playlist.application.port.`in`.*
import ordilov.lissn.playlist.application.service.PlaylistFacade
import ordilov.lissn.playlist.domain.Main
import ordilov.lissn.security.userinfo.UserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/playlists")
class PlaylistController(
    val playlistFacade: PlaylistFacade
) {

    @PostMapping
    fun createPlaylist(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: CreateRequest
    ): ResponseEntity<Main> {
        val command = PlaylistCreateRequest(userPrincipal.id, request.title)
        return ResponseEntity.ok(playlistFacade.createPlaylist(command))
    }

    @PostMapping("/youtube")
    fun addYoutubePlaylist(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: AddPlaylistRequest
    ): ResponseEntity<Main> {
        val command = YoutubeListRequest(userPrincipal.id, request)
        return ResponseEntity.ok(playlistFacade.addPlaylistItems(command))
    }

    @PatchMapping("/{playlistId}")
    fun updatePlaylist(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable playlistId: Long,
        @RequestBody request: UpdateRequest
    ): ResponseEntity<String> {
        val command = PlaylistUpdateRequest(userPrincipal.id, playlistId, request.title)
        playlistFacade.updatePlaylist(command)
        return ResponseEntity.ok("Playlist updated")
    }

    @DeleteMapping("/{playlistId}")
    fun deletePlaylist(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable playlistId: Long
    ): ResponseEntity<String> {
        val command = PlaylistDeleteRequest(userPrincipal.id, playlistId)
        playlistFacade.deletePlaylist(command)
        return ResponseEntity.ok("Playlist deleted")
    }

    @PostMapping("/{playlistId}/playlistItems")
    fun addPlaylistItem(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable playlistId: Long,
        @RequestBody request: AddVideoRequest
    ): ResponseEntity<String> {
        val command = YoutubeVideoRequest(userPrincipal.id, playlistId, request)
        playlistFacade.addPlaylistItem(command)
        return ResponseEntity.ok("PlaylistItem added")
    }

    @DeleteMapping("/{playlistId}/playlistItems/{playlistItemId}")
    fun deletePlaylistItem(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable playlistId: Long,
        @PathVariable playlistItemId: Long
    ): ResponseEntity<String> {
        val command =
            PlaylistItemDeleteRequest(userPrincipal.id, playlistId, playlistItemId)
        playlistFacade.deletePlaylistItem(command)
        return ResponseEntity.ok("Playlist deleted")
    }
}