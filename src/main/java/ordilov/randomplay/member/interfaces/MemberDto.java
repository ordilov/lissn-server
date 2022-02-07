package ordilov.randomplay.member.interfaces;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ordilov.randomplay.member.domain.MemberCommand;
import ordilov.randomplay.member.domain.MemberInfo;

import javax.validation.constraints.NotEmpty;

public class MemberDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest {
        @NotEmpty(message = "아이디는 빈 값이 될 수 없습니다.")
        private String id;

        @NotEmpty(message = "아이디는 빈 값이 될 수 없습니다.")
        private String email;

        @NotEmpty(message = "이름은 빈 값이 될 수 없습니다.")
        private String name;

        public MemberCommand toCommand() {
            return MemberCommand.builder()
                    .id(id)
                    .email(email)
                    .name(name)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterResponse {
        private final Long id;
        private final String email;
        private final String name;

        public RegisterResponse(MemberInfo memberInfo) {
            this.email = memberInfo.getEmail();
            this.id = memberInfo.getId();
            this.name = memberInfo.getName();
        }
    }


}
