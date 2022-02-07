package ordilov.randomplay.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberCommand {
  private final String id;
  private final String email;
  private final String name;
  private final String profileImageUrl;

  public Member toEntity(){
      return Member.builder()
              .email(email)
              .name(name)
              .profileImageUrl(profileImageUrl)
              .build();
  }
}
