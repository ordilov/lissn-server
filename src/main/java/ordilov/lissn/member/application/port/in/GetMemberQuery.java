package ordilov.lissn.member.application.port.in;

import java.util.Optional;
import ordilov.lissn.member.domain.MemberInfo.GetMemberInfo;

public interface GetMemberQuery {
  GetMemberInfo getMember(Long memberId);

  Optional<GetMemberInfo> getMemberByEmail(String email);
}
