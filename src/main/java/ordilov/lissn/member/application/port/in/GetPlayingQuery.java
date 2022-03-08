package ordilov.lissn.member.application.port.in;

import ordilov.lissn.member.domain.playing.PlayingInfo;

public interface GetPlayingQuery {
  PlayingInfo getPlayingInfo(Long memberId);
}
