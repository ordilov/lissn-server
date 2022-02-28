package ordilov.randomplay.member.domain;


import ordilov.randomplay.member.domain.playing.PlayingCommand;
import ordilov.randomplay.member.domain.playing.PlayingInfo;

public interface MemberService {

  MemberInfo login(MemberCommand command);

  MemberInfo getMemberInfo(Long id);

  void updateRefreshToken(Long id, String refreshToken);

  PlayingInfo getPlayingInfo(Long memberId);

  PlayingInfo changePlaying(PlayingCommand.PlayingPlaylist command);

  void deletePlaying(Long memberId, Long playingId);
}
