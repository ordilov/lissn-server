package ordilov.randomplay.member.domain.playing;

public interface PlayingReader {

  PlayingInfo getPlayingByMember(Long memberId);
}
