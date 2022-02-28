package ordilov.randomplay.member.domain.playing;

import java.util.List;

public interface PlayingStore {
  void clear(Long memberId);
  Playing store(Playing playing);
  List<Playing> store(List<Playing> playings);
  void delete(Long playingId);
}
