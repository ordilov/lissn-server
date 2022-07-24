package ordilov.lissn.member.application.port.`in`

import ordilov.lissn.member.domain.GetMemberInfo
import ordilov.lissn.member.domain.RefreshInfo

interface AuthCommand {

    fun signUp(command: RegisterCommand): GetMemberInfo

    fun refresh(refreshToken: String): RefreshInfo

}