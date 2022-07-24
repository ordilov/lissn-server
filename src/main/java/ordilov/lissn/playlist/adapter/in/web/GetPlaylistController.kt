package ordilov.lissn.playlist.adapter.`in`.web

import ordilov.lissn.playlist.application.port.`in`.GetPlaylistQuery
import ordilov.lissn.playlist.domain.Info
import ordilov.lissn.playlist.domain.Main
import ordilov.lissn.security.userinfo.UserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/playlists")
class GetPlaylistController(
    val getPlaylistQuery: GetPlaylistQuery
) {

    @GetMapping("/random")
    fun getRandomPlaylist(
        @AuthenticationPrincipal userPrincipal: UserPrincipal?
    ): ResponseEntity<Main> {
        val memberId = userPrincipal?.id!!
        return ResponseEntity.ok(getPlaylistQuery.getRandomPlaylist(memberId))
    }

    @GetMapping
    fun getPlaylists(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<List<Main>> {
        return ResponseEntity.ok(getPlaylistQuery.getMyPlaylists(userPrincipal.id))
    }

    @GetMapping("/{id}")
    fun getPlaylist(
        @AuthenticationPrincipal userPrincipal: UserPrincipal?,
        @PathVariable("id") id: Long
    ): ResponseEntity<Info> {
        val memberId = userPrincipal?.id!!
        return ResponseEntity.ok(getPlaylistQuery.getPlaylist(id, memberId))
    }
}