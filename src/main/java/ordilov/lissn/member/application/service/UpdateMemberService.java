package ordilov.lissn.member.application.service;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.member.application.port.in.MemberCommand.UpdateCommand;
import ordilov.lissn.member.application.port.in.UpdateMemberCommand;
import ordilov.lissn.member.application.port.out.MemberStore;
import ordilov.lissn.member.domain.AuthInfo.TokenInfo;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.member.domain.MemberInfo.UpdateMemberInfo;
import ordilov.lissn.security.TokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateMemberService implements UpdateMemberCommand {

  private final MemberStore memberStore;
  private final TokenProvider tokenProvider;

  @Override
  public UpdateMemberInfo updateProfile(UpdateCommand command) {
    Member member = memberStore.update(command);
    TokenInfo tokenInfo = new TokenInfo(member);
    String accessToken = tokenProvider.createToken(tokenInfo);
    String newRefreshToken = tokenProvider.createRefreshToken(tokenInfo);
    return new UpdateMemberInfo(member, accessToken, newRefreshToken);
  }

  @Override
  public void deleteMember() {

  }
}
