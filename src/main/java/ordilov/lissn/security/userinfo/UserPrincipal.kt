package ordilov.lissn.security.userinfo;

import com.fasterxml.jackson.databind.ObjectMapper
import ordilov.lissn.member.domain.AuthProvider
import ordilov.lissn.member.domain.TokenInfo
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User
import java.util.*

open class UserPrincipal(
    val id: Long,
    val provider: AuthProvider,
    attributes: MutableMap<String, Any>,
    authorities: MutableCollection<out GrantedAuthority>
) : OAuth2User {

    val attributes: MutableMap<String, Any> = attributes
        @JvmName("getAttributes_") get
    val authorities: MutableCollection<out GrantedAuthority> = authorities
        @JvmName("getAuthorities_") get

    constructor(tokenInfo: TokenInfo) : this(
        id = tokenInfo.id,
        provider = tokenInfo.provider,
        ObjectMapper().convertValue(tokenInfo, Map::class.java) as MutableMap<String, Any>,
        Collections.singleton(SimpleGrantedAuthority("ROLE_USER"))
    )

    override fun getName(): String {
        return id.toString()
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return attributes
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    fun getTokenInfo(): TokenInfo {
        return TokenInfo(
            id,
            attributes["name"].toString(),
            attributes["email"].toString(),
            attributes["picture"].toString(),
            provider
        )
    }
}