package ordilov.lissn.security;

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import ordilov.lissn.config.AppProperties
import ordilov.lissn.member.domain.AuthProvider
import ordilov.lissn.member.domain.TokenInfo
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class TokenProvider(appProperties: AppProperties) {
    private final val key: Key
    private final val ACCESS_EXPIRES_IN: Long
    private final val REFRESH_EXPIRES_IN: Long

    init {
        ACCESS_EXPIRES_IN = appProperties.tokenExpiration
        REFRESH_EXPIRES_IN = appProperties.tokenRefreshExpiration
        key = Keys.hmacShaKeyFor(appProperties.tokenSecret?.toByteArray())
    }

    fun createToken(tokenInfo: TokenInfo): String{
        val claims = getClaims(tokenInfo)
        return buildJwt("ACCESS_TOKEN", claims)
    }

    fun createRefreshToken(tokenInfo: TokenInfo): String{
        val claims = getClaims(tokenInfo)
        return buildJwt("REFRESH_TOKEN", claims)
    }

    fun getClaims(tokenInfo: TokenInfo): Claims {
        val claims = Jwts.claims()
        claims["id"] = tokenInfo.id
        claims["provider"] = tokenInfo.provider
        claims["name"] = tokenInfo.name
        claims["email"] = tokenInfo.email
        claims["picture"] = tokenInfo.picture
        return claims
    }

    fun getTokenInfo(token: String): TokenInfo {
        val claims = parseClaims(token)
        return TokenInfo(
            claims.get("id", Long::class.java),
            claims.get("name", String::class.java),
            claims.get("email", String::class.java),
            claims.get("picture", String::class.java),
            AuthProvider.valueOf(claims.get("provider", String::class.java))
        )
    }

    fun validateToken(authToken: String) {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken)
    }

    fun buildJwt(subject: String, claims: Claims): String {
        val now = Date()
        val expiryDate = Date(now.time + ACCESS_EXPIRES_IN)
        return Jwts.builder()
            .signWith(key)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .setId(claims.id)
            .setSubject(subject)
            .addClaims(claims)
            .compact()
    }

    fun parseClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}