package ordilov.randomplay.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberCommand {

  private final String id;
  private final String name;
  private final String email;
  private final AuthProvider provider;
  private final String profileImageUrl;

  public Member toEntity() {
    return Member.builder()
        .name(name)
        .email(email)
        .provider(provider)
        .profileImageUrl(profileImageUrl)
        .build();
  }

}
