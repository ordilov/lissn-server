package ordilov.randomplay.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.config.AppProperties;
import ordilov.randomplay.security.userinfo.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenProvider {

  private final Key key;
  private final Long EXPIRED_TIME_IN_MILLISECONDS;

  public TokenProvider(AppProperties appProperties) {
    key = Keys.hmacShaKeyFor(appProperties.getTokenSecret().getBytes());
    EXPIRED_TIME_IN_MILLISECONDS = appProperties.getTokenExpiration();
  }

  public String createToken(Authentication authentication) {
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + EXPIRED_TIME_IN_MILLISECONDS);

    return Jwts.builder()
        .signWith(key)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .setClaims(userPrincipal.toClaims())
        .setSubject(userPrincipal.getId().toString())
        .compact();
  }

  public Claims getClaimsFromToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
      return true;
    } catch (SecurityException ex) {
      log.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }

}
