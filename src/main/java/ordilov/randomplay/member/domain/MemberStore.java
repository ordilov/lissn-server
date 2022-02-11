package ordilov.randomplay.member.domain;

public interface MemberStore {
    Member store(Member member);
    void update(Member member, MemberCommand command);
}
