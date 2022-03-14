package ordilov.lissn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.config.AppProperties;
import ordilov.lissn.member.domain.AuthInfo.TokenInfo;
import ordilov.lissn.member.domain.AuthProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenProvider {

  private final Key key;
  private final Long ACCESS_EXPIRES_IN;
  private final Long REFRESH_EXPIRES_IN;

  public TokenProvider(AppProperties appProperties) {
    ACCESS_EXPIRES_IN = appProperties.getTokenExpiration();
    REFRESH_EXPIRES_IN = appProperties.getTokenRefreshExpiration();
    key = Keys.hmacShaKeyFor(appProperties.getTokenSecret().getBytes());
  }

  public String createToken(TokenInfo tokenInfo){
    Claims claims = getClaims(tokenInfo);
    return buildJwt("ACCESS_TOKEN", claims);
  }

  public String createRefreshToken(TokenInfo tokenInfo){
    Claims claims = getClaims(tokenInfo);
    return buildJwt("REFRESH_TOKEN", claims);
  }

  @NotNull
  private Claims getClaims(TokenInfo tokenInfo) {
    Claims claims = Jwts.claims();
    claims.put("id", tokenInfo.getId());
    claims.put("provider", tokenInfo.getProvider());
    claims.put("name", tokenInfo.getName());
    claims.put("email", tokenInfo.getEmail());
    claims.put("picture", tokenInfo.getPicture());
    return claims;
  }

  public TokenInfo getTokenInfo(String token) {
    Claims claims = parseClaims(token);
    return new TokenInfo(
        claims.get("id", Long.class),
        claims.get("name", String.class),
        claims.get("email", String.class),
        claims.get("picture", String.class),
        AuthProvider.valueOf(claims.get("provider", String.class))
    );
  }

  public void validateToken(String authToken) {
    Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
  }

  private String buildJwt(String subject, Claims claims) {
    Date now = new Date();
    Date expiryDate = new Date(
        now.getTime() + (subject.equals("ACCESS_TOKEN") ? ACCESS_EXPIRES_IN : REFRESH_EXPIRES_IN));

    return Jwts.builder()
        .signWith(key)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .setId(claims.getId())
        .setSubject(subject)
        .addClaims(claims)
        .compact();
  }

  private Claims parseClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }
}
