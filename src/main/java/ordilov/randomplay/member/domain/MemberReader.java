package ordilov.randomplay.member.domain;

import java.util.Optional;

public interface MemberReader {
    Optional<Member> getMemberBy(String id);
    Optional<Member> getMemberByEmail(String email);
}
