package ordilov.lissn.member.adapter.out.persistence.playing;

import java.util.List;
import ordilov.lissn.member.domain.playing.Playing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayingRepository extends JpaRepository<Playing, Long> {

  void deleteByMemberId(Long memberId);

  List<Playing> findByMemberId(Long memberId);
}
