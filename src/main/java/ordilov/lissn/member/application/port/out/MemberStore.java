package ordilov.lissn.member.application.port.out;

import ordilov.lissn.member.domain.Member;
import ordilov.lissn.member.application.port.in.MemberCommand;

public interface MemberStore {
    Member store(Member member);
    void update(Member member, MemberCommand command);
}
