package ordilov.lissn.member.application.port.in;

import lombok.Value;
import ordilov.lissn.member.domain.AuthProvider;
import ordilov.lissn.member.domain.Member;

public class MemberCommand {

  @Value
  public static class RegisterCommand {
    String id;
    String name;
    String email;
    String picture;
    AuthProvider provider;

    public Member toEntity() {
      return Member.builder()
          .name(name)
          .email(email)
          .provider(provider)
          .picture(picture)
          .build();
    }


  }

  @Value
  public static class UpdateCommand {
    Long id;
    String name;
    String picture;
  }


}
