package ordilov.randomplay.member.domain;


import ordilov.randomplay.member.domain.playing.PlayingCommand;
import ordilov.randomplay.member.domain.playing.PlayingInfo;
import ordilov.randomplay.member.interfaces.MemberDto.SavePlayingRequest;

public interface MemberService {

  MemberInfo login(MemberCommand command);

  MemberInfo getMemberInfo(Long id);

  void updateRefreshToken(Long id, String refreshToken);

  PlayingInfo getPlayingInfo(Long memberId);

  void changePlaying(PlayingCommand.PlayingPlaylist command);

  void deletePlaying(Long memberId, Long playingId);

  void savePlaying(Long memberId, Long playingId);
}
