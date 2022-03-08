package ordilov.lissn.member.application.port.out;

import java.util.Optional;
import ordilov.lissn.member.domain.Member;

public interface MemberReader {

  Member getMemberBy(Long id);

  Optional<Member> getMemberByEmail(String email);
}
