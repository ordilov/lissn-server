package ordilov.lissn.member.application.port.in;

import static ordilov.lissn.member.application.port.in.MemberCommand.RegisterCommand;

import ordilov.lissn.member.domain.AuthInfo.RefreshInfo;
import ordilov.lissn.member.domain.MemberInfo.GetMemberInfo;

public interface AuthCommand {

  GetMemberInfo signUp(RegisterCommand command);

  RefreshInfo refresh(String refreshToken);

}
