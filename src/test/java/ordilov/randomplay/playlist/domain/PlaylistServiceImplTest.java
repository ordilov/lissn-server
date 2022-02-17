package ordilov.randomplay.playlist.domain;

import ordilov.randomplay.member.domain.AuthProvider;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberReader;
import ordilov.randomplay.member.domain.MemberStore;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeVideoRequest;
import ordilov.randomplay.playlist.domain.youtube.YoutubeVideo;
import ordilov.randomplay.playlist.interfaces.PlaylistDto.AddVideoRequest;
import ordilov.randomplay.track.domain.TrackStore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class PlaylistServiceImplTest {

  @Autowired
  MemberStore memberStore;

  @Autowired
  PlaylistStore playlistStore;

  @Autowired
  PlaylistReader playlistReader;

  @Autowired
  PlaylistService playlistService;

  private Member member;

  private Playlist playlist;

  @BeforeAll
  void setup() {
    member = memberStore.store(
        new Member("ordilov", "asd@gmail.com", "1234", "asd", AuthProvider.google));
    playlist = playlistStore.store(new Playlist("재생목록", member));
  }

  @Test
  @DisplayName("아이템 추가 테스트")
  void addPlaylistItem() {
    YoutubeVideoRequest request = new YoutubeVideoRequest(member.getId(),
        playlist.getId(), new AddVideoRequest("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));

    YoutubeVideo youtubeVideo = new YoutubeVideo();

    playlistService.addPlaylistItem(request, youtubeVideo);

  }
}