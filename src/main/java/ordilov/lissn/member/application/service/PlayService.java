package ordilov.lissn.member.application.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import lombok.RequiredArgsConstructor;
import ordilov.lissn.member.application.port.in.PlayCommand;
import ordilov.lissn.member.application.port.out.MemberReader;
import ordilov.lissn.member.domain.playing.Playing;
import ordilov.lissn.member.domain.playing.PlayingCommand.PlayingPlaylist;
import ordilov.lissn.member.domain.playing.PlayingReader;
import ordilov.lissn.member.domain.playing.PlayingStore;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.playlist.domain.Playlist;
import ordilov.lissn.playlist.domain.PlaylistItem;
import ordilov.lissn.playlist.domain.PlaylistReader;
import ordilov.lissn.track.domain.Track;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayService implements PlayCommand {

  private final MemberReader memberReader;
  private final PlayingStore playingStore;
  private final PlayingReader playingReader;
  private final PlaylistReader playlistReader;

  @Override
  public void changePlaying(PlayingPlaylist command) {
    playingStore.clear(command.getMemberId());

    Member member = memberReader.getMemberBy(command.getMemberId());
    Playlist playlist = playlistReader.getPlaylistBy(command.getPlaylistId());

    List<Track> tracks = playlist.getPlaylistItems().stream()
        .map(PlaylistItem::getTrack)
        .collect(Collectors.toList());

    List<Playing> playings = playingStore.store(LongStream.range(0, tracks.size())
        .mapToObj(i -> new Playing(member, tracks.get((int) i), i))
        .collect(Collectors.toList()));

    if (command.getPlaylistItemId() == null) {
      member.playTrack(playings.get(0).getId());
    } else {
      long selected = IntStream.range(0, playlist.getPlaylistItems().size())
          .filter(i -> Objects.equals(playlist.getPlaylistItems().get(i).getId(),
              command.getPlaylistId()))
          .findFirst().orElse(-1);
      member.playTrack(selected);
    }
  }

  @Override
  public void deletePlaying(Long memberId, Long playingId) {
    Member member = memberReader.getMemberBy(memberId);
    long nowPlaying = member.getNowPlaying();
    if (nowPlaying != 0) {
      member.playTrack(--nowPlaying);
    }
    playingStore.delete(playingId);
  }

  @Override
  public void savePlaying(Long memberId, Long playingId) {
    Member member = memberReader.getMemberBy(memberId);
    member.playTrack(playingId);
  }


}
