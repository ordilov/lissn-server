package ordilov.lissn.common;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import ordilov.lissn.member.domain.Member;

public class MemberTestData {

  public static Member defaultMember() {
    Member member = spy(Member.class);
    when(member.getId()).thenReturn(1L);
//    when(member.getName()).thenReturn("name");
//    when(member.getEmail()).thenReturn("email");
//    when(member.getProfileImageUrl()).thenReturn("profileImageUrl");
//    when(member.getProvider()).thenReturn(AuthProvider.google);
    return member;
  }
}
