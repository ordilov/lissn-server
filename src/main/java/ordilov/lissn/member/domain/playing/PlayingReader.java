package ordilov.lissn.member.domain.playing;

public interface PlayingReader {

  PlayingInfo getPlayingByMember(Long memberId);
}
