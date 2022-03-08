package ordilov.lissn.like.domain;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ordilov.lissn.like.domain.LikeCommand.LikePlaylistRequest;
import ordilov.lissn.like.domain.LikeCommand.LikeTrackRequest;
import ordilov.lissn.like.domain.LikeInfo.LikedPlaylistInfo;
import ordilov.lissn.like.domain.LikeInfo.LikedTrackInfo;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.member.application.port.out.MemberReader;
import ordilov.lissn.playlist.domain.Playlist;
import ordilov.lissn.playlist.domain.PlaylistReader;
import ordilov.lissn.track.domain.Track;
import ordilov.lissn.track.domain.TrackReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

  private final LikeStore likeStore;
  private final LikeReader likeReader;
  private final TrackReader trackReader;
  private final MemberReader memberReader;
  private final PlaylistReader playlistReader;

  @Override
  public LikedPlaylistInfo like(LikePlaylistRequest request) {
    Member member = memberReader.getMemberBy(request.getMemberId());
    Playlist playlist = playlistReader.getPlaylistBy(request.getPlaylistId());
    Optional<LikedPlaylist> likedPlaylist = likeReader.getLikedPlaylist(member, playlist);
    likedPlaylist.ifPresentOrElse(
        likeStore::delete,
        () -> likeStore.store(new LikedPlaylist(member, playlist))
    );

    return new LikedPlaylistInfo(request.getPlaylistId(), likedPlaylist.isEmpty());
  }

  @Override
  public LikedTrackInfo like(LikeTrackRequest request) {
    Member member = memberReader.getMemberBy(request.getMemberId());
    Track track = trackReader.getTrackBy(request.getTrackId());
    Optional<LikedTrack> likedTrack = likeReader.getLikedTrack(member, track);
    likedTrack.ifPresentOrElse(
        likeStore::delete,
        () -> likeStore.store(new LikedTrack(member, track))
    );

    return new LikedTrackInfo(request.getTrackId(), likedTrack.isEmpty());
  }
}
