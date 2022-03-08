package ordilov.lissn.member.application.port.in;

import ordilov.lissn.member.domain.AuthInfo.RefreshInfo;
import ordilov.lissn.member.domain.MemberInfo;

public interface AuthCommand {

  MemberInfo signUp(MemberCommand command);

  RefreshInfo refresh(String refreshToken);

}
