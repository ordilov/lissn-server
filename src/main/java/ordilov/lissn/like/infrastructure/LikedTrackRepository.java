package ordilov.lissn.like.infrastructure;

import java.util.Optional;
import ordilov.lissn.like.domain.LikedTrack;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.track.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedTrackRepository extends JpaRepository<LikedTrack, Long> {
  Optional<LikedTrack> findByMemberAndTrack(Member member, Track track);
}
