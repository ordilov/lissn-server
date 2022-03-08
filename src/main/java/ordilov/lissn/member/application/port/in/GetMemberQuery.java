package ordilov.lissn.member.application.port.in;

import java.util.Optional;
import ordilov.lissn.member.domain.MemberInfo;

public interface GetMemberQuery {
  MemberInfo getMember(Long memberId);

  Optional<MemberInfo> getMemberByEmail(String email);
}
