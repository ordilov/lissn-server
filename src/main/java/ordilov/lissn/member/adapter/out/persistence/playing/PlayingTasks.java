package ordilov.lissn.member.adapter.out.persistence.playing;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlayingTasks {

  private final NowPlayingRepository nowPlayingRepository;
  private final Long NOW_PLAYING_ID = 1L;

  @Scheduled(cron = "*/10 * * * * *")
  public void reportCurrentTime() {
    Optional<NowPlaying> nowPlayingOptional = nowPlayingRepository.findById(NOW_PLAYING_ID);
    long nowPlaying = nowPlayingOptional.map(NowPlaying::getPlaylistId).orElse(1L);
    nowPlayingOptional.ifPresent(nowPlayingRepository::delete);

    long rand = getRandom(nowPlaying);
    nowPlayingRepository.save(new NowPlaying(NOW_PLAYING_ID, rand));
  }

  private long getRandom(long nowPlaying) {
    long rand = nowPlaying;
    while (rand == nowPlaying) {
      rand = (int) (Math.random() * 4) + 1;
    }
    return rand;
  }
}
