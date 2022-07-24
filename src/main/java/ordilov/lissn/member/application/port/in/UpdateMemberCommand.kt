package ordilov.lissn.member.application.port.`in`

import ordilov.lissn.member.domain.UpdateMemberInfo
import org.springframework.web.multipart.MultipartFile

interface UpdateMemberCommand {

    fun updateName(id: Long, name: String): UpdateMemberInfo

    fun updatePicture(id: Long, imageFile: MultipartFile): UpdateMemberInfo

    fun deleteMember()
}