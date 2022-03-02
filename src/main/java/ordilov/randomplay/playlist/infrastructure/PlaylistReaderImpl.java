package ordilov.randomplay.playlist.infrastructure;

import static ordilov.randomplay.like.domain.QLikedPlaylist.likedPlaylist;
import static ordilov.randomplay.like.domain.QLikedTrack.likedTrack;
import static ordilov.randomplay.playlist.domain.QPlaylist.playlist;
import static ordilov.randomplay.playlist.domain.QPlaylistItem.playlistItem;
import static ordilov.randomplay.track.domain.QTrack.track;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.member.infrastructure.playing.NowPlaying;
import ordilov.randomplay.member.infrastructure.playing.NowPlayingRepository;
import ordilov.randomplay.playlist.domain.Playlist;
import ordilov.randomplay.playlist.domain.PlaylistInfo.Item;
import ordilov.randomplay.playlist.domain.PlaylistInfo.Main;
import ordilov.randomplay.playlist.domain.PlaylistInfo.PlaylistWithLike;
import ordilov.randomplay.playlist.domain.PlaylistMapper;
import ordilov.randomplay.playlist.domain.PlaylistReader;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PlaylistReaderImpl implements PlaylistReader {

  private final JPAQueryFactory queryFactory;
  private final PlaylistMapper playlistMapper;
  private final PlaylistRepository playlistRepository;
  private final NowPlayingRepository nowPlayingRepository;

  public PlaylistReaderImpl(EntityManager entityManager,
      PlaylistRepository playlistRepository,
      PlaylistMapper playlistMapper,
      NowPlayingRepository nowPlayingRepository) {
    this.playlistRepository = playlistRepository;
    this.playlistMapper = playlistMapper;
    this.queryFactory = new JPAQueryFactory(entityManager);
    this.nowPlayingRepository = nowPlayingRepository;
  }

  @Override
  public List<Playlist> getPlaylists() {
    return playlistRepository.findAll();
  }

  @Override
  public Playlist getPlaylistBy(Long id) {
    List<Tuple> results = queryFactory
        .select(playlist, playlistItem, track)
        .from(playlist)
        .leftJoin(playlist.playlistItems, playlistItem)
        .leftJoin(playlistItem.track, track)
        .where(playlist.id.eq(id))
        .fetch();

    return results.get(0).get(playlist);
  }

  @Override
  public PlaylistWithLike getRandomPlaylist(Long memberId) {
    Optional<NowPlaying> nowPlayingId = nowPlayingRepository.findById(1L);
    Long nowPlaying = nowPlayingId.isPresent() ? nowPlayingId.get().getPlaylistId() : 1L;

    List<Tuple> results = queryFactory
        .select(playlist, playlistItem, track, likedTrack, likedPlaylist)
        .from(playlist)
        .leftJoin(playlist.likedPlaylists, likedPlaylist)
        .leftJoin(playlist.playlistItems, playlistItem)
        .leftJoin(playlistItem.track, track)
        .leftJoin(track.likedTracks, likedTrack)
        .where(playlist.id.eq(nowPlaying))
        .fetch();

    if (results.isEmpty()) {
      return null;
    }

    PlaylistWithLike playlistWithLike = new PlaylistWithLike(
        Objects.requireNonNull(results.get(0).get(playlist)),
        results.get(0).get(likedPlaylist) != null);

    results.forEach(result -> playlistWithLike.getItems()
        .add(new Item(Objects.requireNonNull(result.get(playlistItem)),
            result.get(likedTrack) != null)));

    return playlistWithLike;
  }

  @Override
  public List<Main> getPlaylistByMember(Long memberId) {
    List<Tuple> results = queryFactory
        .select(playlist, playlistItem, track, likedTrack, likedPlaylist)
        .from(playlist)
        .leftJoin(playlist.likedPlaylists, likedPlaylist)
        .leftJoin(playlist.playlistItems, playlistItem)
        .leftJoin(playlistItem.track, track)
        .leftJoin(track.likedTracks, likedTrack)
        .where(playlist.member.id.eq(memberId))
        .fetch();

    Map<PlaylistWithLike, List<Item>> playlistWithLikes = new HashMap<>();

    results.forEach(tuple -> {
      PlaylistWithLike playlistWithLike = new PlaylistWithLike(
          Objects.requireNonNull(tuple.get(playlist)),
          tuple.get(likedPlaylist) != null);
      playlistWithLikes.computeIfAbsent(playlistWithLike,
          k -> playlistWithLike.getItems());

      if (tuple.get(playlistItem) == null) {
        return;
      }

      playlistWithLikes.get(playlistWithLike)
          .add(new Item(Objects.requireNonNull(tuple.get(playlistItem)),
              tuple.get(likedTrack) != null));
    });

    return playlistMapper.of(new ArrayList<>(playlistWithLikes.keySet()));
  }

}
