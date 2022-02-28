package ordilov.randomplay.member.application;

import lombok.RequiredArgsConstructor;
import ordilov.randomplay.member.domain.MemberCommand;
import ordilov.randomplay.member.domain.MemberInfo;
import ordilov.randomplay.member.domain.MemberService;
import ordilov.randomplay.member.domain.playing.PlayingCommand;
import ordilov.randomplay.member.domain.playing.PlayingInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFacade {

  private final MemberService memberService;

  public MemberInfo login(MemberCommand command) {
    return memberService.login(command);
  }

  public MemberInfo getMember(Long memberId) {
    return memberService.getMemberInfo(memberId);
  }

  public void updateRefreshToken(Long memberId, String refreshToken) {
    memberService.updateRefreshToken(memberId, refreshToken);
  }

  public PlayingInfo getPlayingInfo(Long memberId) {
    return memberService.getPlayingInfo(memberId);
  }

  public PlayingInfo changePlaying(PlayingCommand.PlayingPlaylist command) {
    return memberService.changePlaying(command);
  }

  public void deletePlaying(Long memberId, Long playingId) {
    memberService.deletePlaying(memberId, playingId);
  }
}
