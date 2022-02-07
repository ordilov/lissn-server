package ordilov.randomplay.member.domain;

import lombok.Getter;

@Getter
public class MemberInfo {
    private final Long id;
    private final String email;
    private final String name;
    private final String profileImageUrl;

    public MemberInfo(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.profileImageUrl = member.getProfileImageUrl();
    }
}
