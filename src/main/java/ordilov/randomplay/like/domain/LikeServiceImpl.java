package ordilov.randomplay.like.domain;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ordilov.randomplay.like.domain.LikeCommand.LikePlaylistRequest;
import ordilov.randomplay.like.domain.LikeCommand.LikeTrackRequest;
import ordilov.randomplay.like.domain.LikeInfo.LikedPlaylistInfo;
import ordilov.randomplay.like.domain.LikeInfo.LikedTrackInfo;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberReader;
import ordilov.randomplay.playlist.domain.Playlist;
import ordilov.randomplay.playlist.domain.PlaylistReader;
import ordilov.randomplay.track.domain.Track;
import ordilov.randomplay.track.domain.TrackReader;
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
