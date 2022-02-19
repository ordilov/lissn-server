package ordilov.randomplay.like.infrastructure;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ordilov.randomplay.like.domain.LikeReader;
import ordilov.randomplay.like.domain.LikedPlaylist;
import ordilov.randomplay.like.domain.LikedTrack;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.playlist.domain.Playlist;
import ordilov.randomplay.track.domain.Track;
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
