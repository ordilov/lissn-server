//import com.querydsl.core.Tuple
//import com.querydsl.jpa.impl.JPAQueryFactory
//import ordilov.lissn.member.domain.Member
//import ordilov.lissn.member.domain.playing.PlayingInfo
//import ordilov.lissn.playlist.domain.PlaylistInfo.Item
//import org.springframework.stereotype.Repository
//import java.util.*
//
//@Repository
//class PlayingReaderImpl(
//    val memberReader: ordilov.lissn.member.application.port.out.MemberReader,
//    val queryFactory: JPAQueryFactory
//) : ordilov.lissn.member.domain.playing.PlayingReader {
//
//    override fun getPlayingByMember(memberId: Long): PlayingInfo {
//        val member = memberReader.getMemberBy(memberId)
//        var results = queryFactory
//            .select(playing, track, likedTrack)
//            .from(playing)
//            .leftJoin(playing.track, track)
//            .leftJoin(playing.likedTrack, likedTrack)
//            .where(playing.member.id.eq(memberId))
//            .fetch()
//
//
//
//        return PlayingInfo(
//            member = member,
//            playlist = playing?.playlist?.map { Item(it.id, it.name) } ?: emptyList(),
//            currentItem = playing?.currentItem?.let { Item(it.id, it.name) } ?: Item(0, "")
//        )
//    }
//
//    @Override
//    public PlayingInfo getPlayingByMember(Long memberId)
//    {
//
//        List<Tuple> results = queryFactory
//                .select(playing, track, likedTrack)
//            .from(playing)
//            .leftJoin(playing.track, track)
//            .leftJoin(track.likedTracks, likedTrack)
//            .where(playing.member.eq(member))
//            .fetch();
//
//        results.sort(Comparator.comparing(o -> Objects.requireNonNull(o.get(playing)).getOrder()));
//
//        List<Item> items = results . stream ()
//            .map(result -> new Item(
//        Objects.requireNonNull(result.get(playing)), result.get(likedTrack) != null))
//        .toList();
//
//        return new PlayingInfo (items, member.getNowPlaying());
//    }
//}