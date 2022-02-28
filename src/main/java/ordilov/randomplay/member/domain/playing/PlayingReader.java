package ordilov.randomplay.member.domain.playing;

import java.util.List;

public interface PlayingReader {

  PlayingInfo getPlayingByMember(Long memberId);
}
