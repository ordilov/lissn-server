package ordilov.lissn.member.application.service

import ordilov.lissn.common.application.port.out.UploadImageHandler
import ordilov.lissn.member.application.port.`in`.UpdateMemberCommand
import ordilov.lissn.member.application.port.out.MemberStore
import ordilov.lissn.member.domain.TokenInfo
import ordilov.lissn.member.domain.UpdateMemberInfo
import ordilov.lissn.security.TokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional
class UpdateMemberService(
    val memberStore: MemberStore,
    val tokenProvider: TokenProvider,
    val uploadImageHandler: UploadImageHandler,
) : UpdateMemberCommand {

    override fun updateName(id: Long, name: String): UpdateMemberInfo {
        val member = memberStore.updateName(id, name)
        val tokenInfo =
            TokenInfo(member.id!!, member.name, member.email, member.picture, member.provider)
        val accessToken = tokenProvider.createToken(tokenInfo)
        val newRefreshToken = tokenProvider.createRefreshToken(tokenInfo)

        return UpdateMemberInfo(
            member.id!!,
            member.name,
            member.email,
            member.picture,
            accessToken,
            newRefreshToken
        )
    }

    override fun updatePicture(id: Long, imageFile: MultipartFile): UpdateMemberInfo {
        val picture = uploadImageHandler.upload(id, imageFile)
        val member = memberStore.updatePicture(id, picture)

        val tokenInfo =
            TokenInfo(member.id!!, member.name, member.email, member.picture, member.provider)
        val accessToken = tokenProvider.createToken(tokenInfo)
        val newRefreshToken = tokenProvider.createRefreshToken(tokenInfo)

        return UpdateMemberInfo(
            member.id!!,
            member.name,
            member.email,
            member.picture,
            accessToken,
            newRefreshToken
        )
    }

    override fun deleteMember() {
        TODO("Not yet implemented")
    }
}