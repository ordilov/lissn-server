package ordilov.lissn.member.domain;

import static ordilov.lissn.common.MemberTestData.defaultMember;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

  @Test
  @DisplayName("재생 중인 트랙을 변경한다.")
  void playTrack() {
    Member member = defaultMember();
    member.playTrack(1L);

    assertThat(member.getNowPlaying()).isEqualTo(1L);
  }

  @Test
  @DisplayName("프로필을 변경한다.")
  void updateProfile() {
    Member member = defaultMember();
    member.updateProfile("newName", "newImage");

    assertThat(member.getName()).isEqualTo("newName");
    assertThat(member.getPicture()).isEqualTo("newImage");
  }
}