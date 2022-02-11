package ordilov.randomplay.member.domain;


public interface MemberService {
    MemberInfo login(MemberCommand command);
    MemberInfo getMemberInfo(Long id);
    void updateRefreshToken(Long id, String refreshToken);
}
