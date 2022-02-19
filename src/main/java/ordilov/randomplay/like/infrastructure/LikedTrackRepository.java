package ordilov.randomplay.like.infrastructure;

import java.util.Optional;
import ordilov.randomplay.like.domain.LikedTrack;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.track.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedTrackRepository extends JpaRepository<LikedTrack, Long> {
  Optional<LikedTrack> findByMemberAndTrack(Member member, Track track);
}
