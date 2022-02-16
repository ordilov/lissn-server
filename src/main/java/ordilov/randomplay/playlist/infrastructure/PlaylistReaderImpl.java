package ordilov.randomplay.playlist.infrastructure;

import static ordilov.randomplay.playlist.domain.QPlaylist.playlist;
import static ordilov.randomplay.playlist.domain.QPlaylistItem.playlistItem;
import static ordilov.randomplay.track.domain.QTrack.track;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import ordilov.randomplay.playlist.domain.Playlist;
import ordilov.randomplay.playlist.domain.PlaylistItem;
import ordilov.randomplay.playlist.domain.PlaylistReader;
import ordilov.randomplay.playlist.domain.dto.PlaylistTrackDto;
import org.springframework.stereotype.Component;

@Component
public class PlaylistReaderImpl implements PlaylistReader {

  private final PlaylistRepository playlistRepository;
  private final JPAQueryFactory queryFactory;

  public PlaylistReaderImpl(EntityManager entityManager,
      PlaylistRepository playlistRepository) {
    this.playlistRepository = playlistRepository;
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public List<Playlist> getPlaylists() {
    return playlistRepository.findAll();
  }

  @Override
  public Playlist getPlaylistBy(Long id) {
    return queryFactory
        .select(playlist)
        .from(playlist)
        .leftJoin(playlist.playlistItems, playlistItem)
        .leftJoin(playlistItem.track, track)
        .where(playlist.id.eq(id))
        .fetchOne();
  }

  @Override
  public List<Playlist> getPlaylistByMember(Long memberId) {
    List<PlaylistTrackDto> playlistTracks = queryFactory
        .select(Projections.fields(PlaylistTrackDto.class,
            playlist,
            track
        ))
        .from(playlist)
        .leftJoin(playlist.playlistItems, playlistItem)
        .leftJoin(playlistItem.track, track)
        .where(playlist.member.id.eq(memberId))
        .fetch();

    return playlistTracks.stream().map(PlaylistTrackDto::getPlaylist).distinct()
        .collect(Collectors.toList());
  }

  public List<Playlist> getPlaylistByMembers(Long memberId) {
    return queryFactory
        .select(playlist)
        .from(playlist)
        .where(playlist.member.id.eq(memberId))
        .fetch();
  }

  @Override
  public List<PlaylistItem> getPlaylistItemsByMembers(Long memberId) {
    return null;
  }
}
