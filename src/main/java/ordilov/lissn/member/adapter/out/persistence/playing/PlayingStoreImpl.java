package ordilov.lissn.member.adapter.out.persistence.playing;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ordilov.lissn.member.domain.playing.Playing;
import ordilov.lissn.member.domain.playing.PlayingStore;
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
