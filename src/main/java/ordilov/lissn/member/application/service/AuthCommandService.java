package ordilov.lissn.member.application.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import ordilov.lissn.member.application.port.in.AuthCommand;
import ordilov.lissn.member.application.port.in.MemberCommand;
import ordilov.lissn.member.application.port.out.MemberStore;
import ordilov.lissn.member.domain.AuthInfo.RefreshInfo;
import ordilov.lissn.member.domain.MemberInfo;
import ordilov.lissn.security.TokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthCommandService implements AuthCommand {

  private final MemberStore memberStore;
  private final TokenProvider tokenProvider;

  @Override
  public MemberInfo signUp(MemberCommand command) {
    return new MemberInfo(memberStore.store(command.toEntity()));
  }

  @Override
  public RefreshInfo refresh(String refreshToken) {
    Claims claims = tokenProvider.getClaims(refreshToken);
    String accessToken = tokenProvider.createToken(claims);
    String newRefreshToken = tokenProvider.createRefreshToken(claims);
    return new RefreshInfo(accessToken, newRefreshToken);
  }
}
