package ordilov.lissn.member.application.port.`in`

import ordilov.lissn.member.domain.AuthProvider

data class RegisterCommand(
    val id: String,
    val name: String,
    val email: String,
    val picture: String,
    val provider: AuthProvider
) {
}

data class UpdateCommand(
    val id: Long,
    val name: String,
    val picture: String
) {
}