package ordilov.randomplay.member.application;

import lombok.RequiredArgsConstructor;
import ordilov.randomplay.member.domain.MemberCommand;
import ordilov.randomplay.member.domain.MemberInfo;
import ordilov.randomplay.member.domain.MemberService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberFacade {
    private final MemberService memberService;

    public MemberInfo registerMember(MemberCommand command) {
        return memberService.login(command);
    }

    public MemberInfo getMember(String memberId) {
        return memberService.getMemberInfo(memberId);
    }

}
