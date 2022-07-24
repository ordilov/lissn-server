package ordilov.lissn.member.adapter.`in`.web

import ordilov.lissn.member.application.port.`in`.AuthCommand
import ordilov.lissn.member.domain.RefreshInfo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    val authCommand: AuthCommand
) {

    @PostMapping("/refresh")
    fun refresh(@RequestBody refreshTokenRequest: RefreshTokenRequest): ResponseEntity<RefreshInfo> {
        val refreshInfo = authCommand.refresh(refreshTokenRequest.refreshToken)
        return ResponseEntity.ok(refreshInfo)
    }
}