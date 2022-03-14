package ordilov.lissn.member.application.port.out;

import ordilov.lissn.member.application.port.in.MemberCommand.UpdateCommand;
import ordilov.lissn.member.domain.Member;

public interface MemberStore {
    Member store(Member member);
    Member updateName(Long id, String name);

    Member updatePicture(Long id, String picture);
}
