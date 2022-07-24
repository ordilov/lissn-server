import ordilov.lissn.member.application.port.`in`.GetPlayingQuery
import ordilov.lissn.member.application.port.`in`.PlayCommand
import ordilov.lissn.member.domain.playing.PlayingInfo
import ordilov.lissn.member.domain.playing.PlayingPlaylistDto
import ordilov.lissn.security.userinfo.UserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/playing")
class PlayingController(
    val playCommand: PlayCommand,
    val getPlayingQuery: GetPlayingQuery
) {

    @GetMapping
    fun getPlayingInfo(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<PlayingInfo> {
        val playingInfo = getPlayingQuery.getPlayingInfo(userPrincipal.id)
        return ResponseEntity.ok(playingInfo)
    }

    @PostMapping
    fun playPlaylist(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: ChangePlayingRequest
    ): ResponseEntity<String> {
        val playingCommand =
            PlayingPlaylistDto(userPrincipal.id, request.playlistId, request.playlistItemId)
        playCommand.addPlaying(playingCommand)
        return ResponseEntity.ok("playing added")
    }

    @PutMapping
    fun changePlaylist(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: ChangePlayingRequest
    ): ResponseEntity<String> {
        val playingCommand =
            PlayingPlaylistDto(userPrincipal.id, request.playlistId, request.playlistItemId)
        playCommand.changePlaying(playingCommand)
        return ResponseEntity.ok("playing added")
    }

    @PostMapping("/{playingId}")
    fun playTrack(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable playingId: Long
    ): ResponseEntity<String> {
        playCommand.playTrack(userPrincipal.id, playingId)
        return ResponseEntity.ok("playing saved")
    }

    @DeleteMapping("/{playingId}")
    fun cancelPlaying(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable playingId: Long
    ): ResponseEntity<String> {
        playCommand.deletePlaying(userPrincipal.id, playingId)
        return ResponseEntity.ok("playing deleted")
    }
}