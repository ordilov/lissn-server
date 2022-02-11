package ordilov.randomplay.member.domain;

import java.util.Optional;

public interface MemberReader {

  Member getMemberBy(Long id);

  Optional<Member> getMemberByEmail(String email);
}
