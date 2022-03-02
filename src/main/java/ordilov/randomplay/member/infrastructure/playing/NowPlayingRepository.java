package ordilov.randomplay.member.infrastructure.playing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NowPlayingRepository extends JpaRepository<NowPlaying, Long> {

}
