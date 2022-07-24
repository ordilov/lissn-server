package ordilov.lissn.playlist.adapter.out.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import ordilov.lissn.member.adapter.out.persistence.playing.HomePlayingRepository
import ordilov.lissn.playlist.application.port.out.PlaylistReader
import ordilov.lissn.playlist.domain.*
import org.springframework.stereotype.Repository

@Repository
class PlaylistReaderImpl(
    val queryFactory: JPAQueryFactory,
    val playlistMapper: PlaylistMapper,
    val playlistRepository: PlaylistRepository,
    val homePlayingRepository: HomePlayingRepository,
) : PlaylistReader {

    //    override fun getPlaylists(): List<Playlist> {
//        return playlistRepository.findAll()
//    }
//
//    override fun getPlaylistInfo(id: Long, memberId: Long): Info {
//        val p = queryFactory
//            .select(
//                Projections.fields(
//                    Info::class.java,
//                    playlist.id,
//                    playlist.title,
//                    playlist.member.name.as("author"),
//                    playlist.member.id.as("authorId")
//                )
//            )
//            .from(playlist)
//            .join(playlist.member)
//            .leftJoin(playlist.likedPlaylists, likedPlaylist)
//            .where(playlist.id.eq(id))
//            .fetchOne()
//
//        val results = queryFactory
//            .select(
//                Projections.fields(
//                    Item.class,
//                            playlistItem . id,
//                    track.title,
//                    track.id.as("trackId"),
//                    track.resourceId.as("resourceId"),
//                    likedTrack.isNotNull().as("isLiked")
//                )
//            )
//            .from(playlistItem)
//            .join(playlistItem.track, track)
//            .leftJoin(track.likedTracks, likedTrack)
//            .where(playlistItem.playlist.id.eq(id))
//            .fetch()
//
//        p.getItems().addAll(results)
//
//        return p
//    }
//
//    override fun getPlaylistBy(id: Long): Playlist {
//        val results = queryFactory
//            .select(playlist, playlistItem, track)
//            .from(playlist)
//            .leftJoin(playlist.playlistItems, playlistItem)
//            .leftJoin(playlistItem.track, track)
//            .where(playlist.id.eq(id))
//            .fetch();
//
//        return results.get(0).get(playlist);
//    }
//
//    override fun getRandomPlaylist(memberId: Long): PlaylistWithLike {
//        val nowPlayingId = homePlayingRepository.findById(1L);
//        val nowPlaying = nowPlayingId.isPresent() ? nowPlayingId.get().getPlaylistId() : 1L
//
//        val results = queryFactory
//            .select(playlist, playlistItem, track, likedTrack, likedPlaylist)
//            .from(playlist)
//            .leftJoin(playlist.likedPlaylists, likedPlaylist)
//            .leftJoin(playlist.playlistItems, playlistItem)
//            .leftJoin(playlistItem.track, track)
//            .leftJoin(track.likedTracks, likedTrack)
//            .where(playlist.id.eq(nowPlaying))
//            .fetch()
//
//        if (results.isEmpty()) {
//            return null
//        }
//
//        val playlistWithLike = PlaylistWithLike(
//            results.get(0).get(playlist),
//            results.get(0).get(likedPlaylist) != null
//        )
//
//        results.forEach(result -> playlistWithLike.getItems()
//        .add(
//            Item(
//                result.get(playlistItem),
//                result.get(likedTrack) != null
//            )
//        ))
//
//        return playlistWithLike
//    }
//
//    override fun getPlaylistByMember(memberId: Long): List<Main> {
//        val results = queryFactory
//            .select(playlist, playlistItem, track, likedTrack, likedPlaylist)
//            .from(playlist)
//            .leftJoin(playlist.likedPlaylists, likedPlaylist)
//            .leftJoin(playlist.playlistItems, playlistItem)
//            .leftJoin(playlistItem.track, track)
//            .leftJoin(track.likedTracks, likedTrack)
//            .where(playlist.member.id.eq(memberId))
//            .fetch();
//
//        Map<PlaylistWithLike, List<Item>> playlistWithLikes = new HashMap<>();
//
//        results.forEach(tuple -> {
//            PlaylistWithLike playlistWithLike = new PlaylistWithLike(
//                Objects.requireNonNull(tuple.get(playlist)),
//                tuple.get(likedPlaylist) != null
//            );
//            playlistWithLikes.computeIfAbsent(
//                playlistWithLike,
//                k -> playlistWithLike.getItems());
//
//            if (tuple.get(playlistItem) == null) {
//                return;
//            }
//
//            playlistWithLikes.get(playlistWithLike)
//                .add(
//                    new Item (Objects.requireNonNull(tuple.get(playlistItem)),
//                    tuple.get(likedTrack) != null
//                ));
//        });
//
//        return playlistMapper.of(new ArrayList < > (playlistWithLikes.keySet()));
//    }
    override fun getPlaylists(): List<Playlist> {
        TODO("Not yet implemented")
    }

    override fun getPlaylistInfo(id: Long, memberId: Long): Info {
        TODO("Not yet implemented")
    }

    override fun getPlaylistBy(id: Long): Playlist {
        TODO("Not yet implemented")
    }

    override fun getRandomPlaylist(memberId: Long): PlaylistWithLike {
        TODO("Not yet implemented")
    }

    override fun getPlaylistByMember(memberId: Long): List<Main> {
        TODO("Not yet implemented")
    }
}