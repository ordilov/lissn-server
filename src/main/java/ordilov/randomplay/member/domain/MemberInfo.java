package ordilov.randomplay.member.domain;

import lombok.Getter;

@Getter
public class MemberInfo {
    private final Long id;
    private final String name;
    private final String email;
    private final String refreshToken;
    private final String profileImageUrl;
    private final AuthProvider provider;

    public MemberInfo(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.provider = member.getProvider();
        this.refreshToken = member.getRefreshToken();
        this.profileImageUrl = member.getProfileImageUrl();
    }
}
