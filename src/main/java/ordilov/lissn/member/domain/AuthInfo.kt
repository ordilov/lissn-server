package ordilov.lissn.member.domain

data class TokenInfo(
    val id: Long,
    val name: String,
    val email: String,
    val picture: String,
    val provider: AuthProvider
)

data class RefreshInfo(
    val accessToken: String,
    val refreshToken: String
)