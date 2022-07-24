package ordilov.lissn.member.domain

data class GetMemberInfo(
    val id: Long,
    val name: String,
    val email: String,
    val picture: String,
    val provider: AuthProvider,
)

data class UpdateMemberInfo(
    val id: Long,
    val name: String,
    val email: String,
    val picture: String,
    val accessToken: String,
    val refreshToken: String
)