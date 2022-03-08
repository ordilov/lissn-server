package ordilov.lissn.member.application.port.in;


import ordilov.lissn.member.domain.playing.PlayingCommand;

public interface PlayCommand {
  void changePlaying(PlayingCommand.PlayingPlaylist command);

  void deletePlaying(Long memberId, Long playingId);

  void savePlaying(Long memberId, Long playingId);
}
