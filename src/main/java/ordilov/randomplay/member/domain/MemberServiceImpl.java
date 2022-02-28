package ordilov.randomplay.member.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import lombok.RequiredArgsConstructor;
import ordilov.randomplay.member.domain.playing.Playing;
import ordilov.randomplay.member.domain.playing.PlayingCommand.PlayingPlaylist;
import ordilov.randomplay.member.domain.playing.PlayingInfo;
import ordilov.randomplay.member.domain.playing.PlayingReader;
import ordilov.randomplay.member.domain.playing.PlayingStore;
import ordilov.randomplay.playlist.domain.Playlist;
import ordilov.randomplay.playlist.domain.PlaylistItem;
import ordilov.randomplay.playlist.domain.PlaylistReader;
import ordilov.randomplay.track.domain.Track;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberStore memberStore;
  private final MemberReader memberReader;
  private final PlayingStore playingStore;
  private final PlayingReader playingReader;
  private final PlaylistReader playlistReader;

  @Override
  public MemberInfo login(MemberCommand command) {
    Member member = memberReader.getMemberByEmail(command.getEmail())
        .orElseGet(() -> memberStore.store(command.toEntity()));
    return new MemberInfo(member);
  }

  @Override
  public void updateRefreshToken(Long id, String refreshToken) {
    Member member = memberReader.getMemberBy(id);
    member.setRefreshToken(refreshToken);
  }

  @Override
  public PlayingInfo getPlayingInfo(Long memberId) {
    return playingReader.getPlayingByMember(memberId);
  }

  @Override
  public PlayingInfo changePlaying(PlayingPlaylist command) {
    playingStore.clear(command.getMemberId());

    Member member = memberReader.getMemberBy(command.getMemberId());
    Playlist playlist = playlistReader.getPlaylistBy(command.getPlaylistId());
    List<Track> tracks = playlist.getPlaylistItems().stream()
        .map(PlaylistItem::getTrack)
        .collect(Collectors.toList());

    playingStore.store(LongStream.range(0, tracks.size())
        .mapToObj(i -> new Playing(member, tracks.get((int) i), i))
        .collect(Collectors.toList()));

    long selected = IntStream.range(0, playlist.getPlaylistItems().size())
        .filter(i -> Objects.equals(playlist.getPlaylistItems().get(i).getId(),
            command.getPlaylistId()))
        .findFirst().orElse(-1);
    member.playTrack(selected);

    member.playTrack(command.getPlaylistItemId());

    return null;
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
  @Transactional(readOnly = true)
  public MemberInfo getMemberInfo(Long id) {
    return new MemberInfo(memberReader.getMemberBy(id));
  }
}
