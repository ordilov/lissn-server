package ordilov.randomplay.member.infrastructure.playing;

import java.util.List;
import ordilov.randomplay.member.domain.playing.Playing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayingRepository extends JpaRepository<Playing, Long> {

  void deleteByMemberId(Long memberId);

  List<Playing> findByMemberId(Long memberId);
}
