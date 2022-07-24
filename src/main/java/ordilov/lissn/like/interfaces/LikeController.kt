package ordilov.lissn.like.interfaces

import ordilov.lissn.like.application.LikeFacade
import ordilov.lissn.like.domain.LikePlaylistCommand
import ordilov.lissn.like.domain.LikeTrackCommand
import ordilov.lissn.like.domain.LikedPlaylistInfo
import ordilov.lissn.like.domain.LikedTrackInfo
import ordilov.lissn.security.userinfo.UserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/likes")
class LikeController(
    val likeFacade: LikeFacade,
) {
    @PostMapping("/playlist")
    fun likePlaylist(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: LikePlaylistRequest
    ): ResponseEntity<LikedPlaylistInfo> {
        val command = LikePlaylistCommand(userPrincipal.id, request.playlistId)
        return ResponseEntity.ok(likeFacade.like(command))
    }

    @PostMapping("/track")
    fun likeTrack(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: LikeTrackRequest
    ): ResponseEntity<LikedTrackInfo> {
        val command = LikeTrackCommand(userPrincipal.id, request.trackId)
        return ResponseEntity.ok(likeFacade.like(command))
    }
}