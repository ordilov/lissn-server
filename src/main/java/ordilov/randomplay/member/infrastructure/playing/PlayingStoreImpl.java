package ordilov.randomplay.member.infrastructure.playing;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ordilov.randomplay.member.domain.playing.Playing;
import ordilov.randomplay.member.domain.playing.PlayingStore;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlayingStoreImpl implements PlayingStore {

  private final PlayingRepository playingRepository;

  @Override
  public void clear(Long memberId) {
    playingRepository.deleteByMemberId(memberId);
  }

  @Override
  public Playing store(Playing playing) {
    return playingRepository.save(playing);
  }

  @Override
  public List<Playing> store(List<Playing> playings) {
    return playingRepository.saveAll(playings);
  }

  @Override
  public void delete(Long playingId) {
    playingRepository.deleteById(playingId);
  }
}
