package ordilov.lissn.member.application.port.out;

import ordilov.lissn.member.application.port.in.MemberCommand.UpdateCommand;
import ordilov.lissn.member.domain.Member;

public interface MemberStore {
    Member store(Member member);
    Member update(UpdateCommand command);
}
