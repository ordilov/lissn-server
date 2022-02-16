package ordilov.randomplay.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import ordilov.randomplay.member.domain.AuthProvider;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberCommand;
import ordilov.randomplay.member.domain.MemberInfo;
import ordilov.randomplay.member.domain.MemberReader;
import ordilov.randomplay.member.domain.MemberService;
import ordilov.randomplay.member.domain.MemberServiceImpl;
import ordilov.randomplay.member.domain.MemberStore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @Mock
  private MemberReader memberReader;

  @Mock
  private MemberStore memberStore;

  private MemberService memberService;

  @BeforeEach
  void setUp() {
    memberService = new MemberServiceImpl(memberStore, memberReader);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  @DisplayName("로그인 시 새로운 회원 등록")
  void signup() {
    Member member = mock(Member.class);
    when(member.getId()).thenReturn(123L);
    when(member.getName()).thenReturn("test");
    when(member.getEmail()).thenReturn("email");
    when(member.getProfileImageUrl()).thenReturn("url");
    when(member.getProvider()).thenReturn(AuthProvider.google);

    when(memberReader.getMemberByEmail("test")).thenReturn(Optional.of(member));

    MemberCommand command = MemberCommand.builder()
        .name("test")
        .profileImageUrl("url")
        .email("test")
        .provider(AuthProvider.google)
        .build();

    MemberInfo memberInfo = memberService.login(command);

    assertThat(memberInfo.getName()).isEqualTo("test");
  }

  @Test
  @DisplayName("로그인 시 이미 존재하는 회원 정보 반환")
  void login() {
    Member member1 = new Member("a", "b", "c", "d", AuthProvider.google);
    when(memberReader.getMemberByEmail("test")).thenReturn(Optional.of(member1));
    Optional<Member> member = memberReader.getMemberByEmail("test");

    assertThat(member.get().getName()).isEqualTo("a");
  }

  @Test
  void getMemberInfo() {

  }

  public class MockMember{
    private Long id;
    private String name;
    private String email;
    private String refreshToken;
    private String profileImageUrl;
    private AuthProvider provider;
    private Boolean isDeleted = false;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getRefreshToken() {
      return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
    }

    public String getProfileImageUrl() {
      return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
      this.profileImageUrl = profileImageUrl;
    }

    public AuthProvider getProvider() {
      return provider;
    }

    public void setProvider(AuthProvider provider) {
      this.provider = provider;
    }

    public Boolean getDeleted() {
      return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
      isDeleted = deleted;
    }
  }
}