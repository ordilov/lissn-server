package ordilov.lissn.member.adapter.out.persistence.playing;

import static ordilov.lissn.like.domain.QLikedTrack.likedTrack;
import static ordilov.lissn.member.domain.playing.QPlaying.playing;
import static ordilov.lissn.track.domain.QTrack.track;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.member.application.port.out.MemberReader;
import ordilov.lissn.member.domain.playing.PlayingInfo;
import ordilov.lissn.member.domain.playing.PlayingReader;
import ordilov.lissn.playlist.domain.PlaylistInfo.Item;
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
