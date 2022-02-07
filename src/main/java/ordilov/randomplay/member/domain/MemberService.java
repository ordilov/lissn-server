package ordilov.randomplay.member.domain;


public interface MemberService {
    MemberInfo login(MemberCommand command);
    MemberInfo getMemberInfo(String id);
}
