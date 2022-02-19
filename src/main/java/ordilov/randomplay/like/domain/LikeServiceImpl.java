package ordilov.randomplay.like.domain;

import lombok.RequiredArgsConstructor;
import ordilov.randomplay.like.domain.LikeCommand.LikePlaylistRequest;
import ordilov.randomplay.like.domain.LikeCommand.LikeTrackRequest;
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
  private final MemberReader memberReader;
  private final PlaylistReader playlistReader;
  private final TrackReader trackReader;

  @Override
  public void like(LikePlaylistRequest request) {
    Member member = memberReader.getMemberBy(request.getMemberId());
    Playlist playlist = playlistReader.getPlaylistBy(request.getPlaylistId());

    likeReader.getLikedPlaylist(member, playlist)
        .ifPresentOrElse(
            likeStore::delete
            , () -> likeStore.store(new LikedPlaylist(member, playlist))
        );
  }

  @Override
  public void like(LikeTrackRequest request) {
    Member member = memberReader.getMemberBy(request.getMemberId());
    Track track = trackReader.getTrackBy(request.getTrackId());

    likeReader.getLikedTrack(member, track)
        .ifPresentOrElse(
            likeStore::delete
            , () -> likeStore.store(new LikedTrack(member, track))
        );
  }

  @Override
  public void unlike(Long memberId, Long playlistId) {

  }

  @Override
  public boolean isLiked(Long memberId, Long playlistId) {
    return false;
  }
}
