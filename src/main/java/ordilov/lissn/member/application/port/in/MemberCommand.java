package ordilov.lissn.member.application.port.in;

import lombok.Builder;
import lombok.Getter;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.member.domain.AuthProvider;

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
