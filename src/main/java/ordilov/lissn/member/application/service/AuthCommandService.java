package ordilov.lissn.member.application.service;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.member.application.port.in.AuthCommand;
import ordilov.lissn.member.application.port.in.MemberCommand.RegisterCommand;
import ordilov.lissn.member.application.port.out.MemberStore;
import ordilov.lissn.member.domain.AuthInfo.RefreshInfo;
import ordilov.lissn.member.domain.AuthInfo.TokenInfo;
import ordilov.lissn.member.domain.MemberInfo.GetMemberInfo;
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
  public GetMemberInfo signUp(RegisterCommand command) {
    return new GetMemberInfo(memberStore.store(command.toEntity()));
  }

  @Override
  public RefreshInfo refresh(String refreshToken) {
    TokenInfo tokenInfo = tokenProvider.getTokenInfo(refreshToken);
    String accessToken = tokenProvider.createToken(tokenInfo);
    String newRefreshToken = tokenProvider.createRefreshToken(tokenInfo);
    return new RefreshInfo(accessToken, newRefreshToken);
  }
}
