package ordilov.lissn.member.adapter.`in`.web

import ordilov.lissn.member.application.port.`in`.GetMemberQuery
import ordilov.lissn.member.domain.GetMemberInfo
import ordilov.lissn.security.userinfo.UserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class GetMemberController(
    val getMemberQuery: GetMemberQuery
) {

    @GetMapping("/{memberId}")
    fun findMember(@PathVariable memberId: Long): ResponseEntity<GetMemberInfo> {
        val memberInfo = getMemberQuery.getMember(memberId)
        return ResponseEntity.ok(memberInfo)
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    fun getCurrentMember(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<GetMemberInfo> {
        val memberInfo = getMemberQuery.getMember(userPrincipal.id)
        return ResponseEntity.ok(memberInfo)
    }
}