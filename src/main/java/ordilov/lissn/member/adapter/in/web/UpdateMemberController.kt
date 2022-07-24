import ordilov.lissn.member.application.port.`in`.UpdateMemberCommand
import ordilov.lissn.member.domain.UpdateMemberInfo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/members/{memberId}")
class UpdateMemberController(
    val updateMemberCommand: UpdateMemberCommand
) {

    @PatchMapping("/name")
    fun updateMember(
        @PathVariable memberId: Long,
        @RequestParam name: String
    ): ResponseEntity<UpdateMemberInfo> {
        return ResponseEntity.ok(updateMemberCommand.updateName(memberId, name));
    }

    @PatchMapping("/picture")
    fun updatePicture(
        @PathVariable memberId: Long,
        @RequestParam("data") file: MultipartFile
    ): ResponseEntity<UpdateMemberInfo> {
        return ResponseEntity.ok(updateMemberCommand.updatePicture(memberId, file));
    }
}