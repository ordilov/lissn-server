package ordilov.randomplay.playlist.domain;

import javax.persistence.EntityManager;
import ordilov.randomplay.member.domain.AuthProvider;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberStore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class PlaylistReaderTest {

  @Autowired
  EntityManager em;

  @Autowired
  PlaylistReader playlistReader;

  @Autowired
  PlaylistStore playlistStore;

  @Autowired
  MemberStore memberStore;

  @BeforeEach
  void setUp() {
    Member member = memberStore.store(
        new Member("a", "b@naver.com", "c", "d", AuthProvider.google));
    playlistStore.store(new Playlist("start", member));
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void getPlaylistByMember() {

  }


  @Test
  @DisplayName("플레이리스트 확인")
  void getPlaylistByIdTest() {
//    List<Short> playlistByMember = playlistReader.getPlaylistShortByMember(1L);
    Playlist playlist = playlistReader.getPlaylistBy(2L);
    System.out.println("playlist.getId() = " + playlist.getId());
//    assertThat(playlist.getMember().getId()).isEqualTo(1L);
  }
}