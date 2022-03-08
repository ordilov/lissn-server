package ordilov.lissn.like.infrastructure;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ordilov.lissn.like.domain.LikeReader;
import ordilov.lissn.like.domain.LikedPlaylist;
import ordilov.lissn.like.domain.LikedTrack;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.playlist.domain.Playlist;
import ordilov.lissn.track.domain.Track;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeReaderImpl implements LikeReader {

  private final LikedTrackRepository likedTrackRepository;
  private final LikedPlaylistRepository likedPlaylistRepository;

  @Override
  public Optional<LikedTrack> getLikedTrack(Member member, Track track) {
    return likedTrackRepository.findByMemberAndTrack(member, track);
  }

  @Override
  public Optional<LikedPlaylist> getLikedPlaylist(Member member, Playlist playlist) {
    return likedPlaylistRepository.findByMemberAndPlaylist(member, playlist);
  }
}
