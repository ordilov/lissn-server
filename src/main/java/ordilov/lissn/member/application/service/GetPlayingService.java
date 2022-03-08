package ordilov.lissn.member.application.service;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.member.application.port.in.GetPlayingQuery;
import ordilov.lissn.member.domain.playing.PlayingInfo;
import ordilov.lissn.member.domain.playing.PlayingReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPlayingService implements GetPlayingQuery {

  private final PlayingReader playingReader;

  @Override
  public PlayingInfo getPlayingInfo(Long memberId) {
    return playingReader.getPlayingByMember(memberId);
  }
}
