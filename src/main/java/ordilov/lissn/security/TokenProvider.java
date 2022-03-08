package ordilov.lissn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.config.AppProperties;
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

  public String createToken(Claims claims) {
    return buildJwt("ACCESS_TOKEN", claims);
  }

  public String createRefreshToken(Claims claims) {
    return buildJwt("REFRESH_TOKEN", claims);
  }

  public Claims getClaims(String token) {
    return parseClaims(token);
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
