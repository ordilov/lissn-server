package ordilov.randomplay.member.infrastructure.playing;

import static ordilov.randomplay.like.domain.QLikedTrack.likedTrack;
import static ordilov.randomplay.member.domain.playing.QPlaying.playing;
import static ordilov.randomplay.track.domain.QTrack.track;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberReader;
import ordilov.randomplay.member.domain.playing.PlayingInfo;
import ordilov.randomplay.member.domain.playing.PlayingReader;
import ordilov.randomplay.playlist.domain.PlaylistInfo.Item;
import org.springframework.stereotype.Repository;

@Repository
public class PlayingReaderImpl implements PlayingReader {

  private final MemberReader memberReader;
  private final JPAQueryFactory queryFactory;

  public PlayingReaderImpl(EntityManager em, MemberReader memberReader) {
    this.memberReader = memberReader;
    this.queryFactory = new JPAQueryFactory(em);
  }

  @Override
  public PlayingInfo getPlayingByMember(Long memberId) {
    Member member = memberReader.getMemberBy(memberId);

    List<Tuple> results = queryFactory
        .select(playing, track, likedTrack)
        .from(playing)
        .leftJoin(playing.track, track)
        .leftJoin(track.likedTracks, likedTrack)
        .where(playing.member.eq(member))
        .fetch();

    results.sort(Comparator.comparing(o -> Objects.requireNonNull(o.get(playing)).getOrder()));

    List<Item> items = results.stream()
        .map(result -> new Item(
            Objects.requireNonNull(result.get(playing)), result.get(likedTrack) != null)).collect(
            Collectors.toList());

    return new PlayingInfo(items, member.getNowPlaying());
  }
}
