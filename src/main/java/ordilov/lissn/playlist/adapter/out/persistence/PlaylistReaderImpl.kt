package ordilov.lissn.playlist.adapter.out.persistence

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import ordilov.lissn.like.domain.QLikedPlaylist.likedPlaylist
import ordilov.lissn.like.domain.QLikedTrack.likedTrack
import ordilov.lissn.member.adapter.out.persistence.playing.HomePlayingRepository
import ordilov.lissn.playlist.application.port.out.PlaylistReader
import ordilov.lissn.playlist.domain.*
import ordilov.lissn.playlist.domain.QPlaylist.playlist
import ordilov.lissn.playlist.domain.QPlaylistItem.playlistItem
import ordilov.lissn.track.domain.QTrack.track
import org.springframework.stereotype.Repository

@Repository
class PlaylistReaderImpl(
    val queryFactory: JPAQueryFactory,
    val playlistMapper: PlaylistMapper,
    val playlistRepository: PlaylistRepository,
    val homePlayingRepository: HomePlayingRepository,
) : PlaylistReader {

    override fun getPlaylists(): List<Playlist> {
        return playlistRepository.findAll()
    }

    override fun getPlaylistInfo(id: Long, memberId: Long): Info {
        val p = queryFactory
            .select(
                Projections.fields(
                    Info::class.java,
                    playlist.id,
                    playlist.title,
                    playlist.member.name.`as`("author"),
                    playlist.member.id.`as`("authorId")
                )
            )
            .from(playlist)
            .join(playlist.member)
            .leftJoin(playlist.likedPlaylists, likedPlaylist)
            .where(playlist.id.eq(id))
            .fetchOne()

        val results = queryFactory
            .select(
                Projections.fields(
                    Item::class.java,
                    playlistItem.id,
                    track.title,
                    track.id.`as`("trackId"),
                    track.resourceId.`as`("resourceId"),
                    likedTrack.isNotNull.`as`("isLiked")
                )
            )
            .from(playlistItem)
            .join(playlistItem.track, track)
            .where(playlistItem.playlist.id.eq(id))
            .fetch()

        if (p == null) {
            throw java.lang.IllegalArgumentException("Playlist not found")
        }

        p.items = results

        return p
    }

    override fun getPlaylistBy(id: Long): Playlist {
        val results = queryFactory
            .select(playlist, playlistItem, track)
            .from(playlist)
            .leftJoin(playlist.playlistItems, playlistItem)
            .leftJoin(playlistItem.track, track)
            .where(playlist.id.eq(id))
            .fetch()

        return results[0].get(playlist) ?: throw IllegalArgumentException("Playlist not found")
    }

    override fun getRandomPlaylist(memberId: Long): PlaylistWithLike {
        val nowPlayingId = homePlayingRepository.findById(1L).orElseThrow().id

        val results = queryFactory
            .select(playlist, playlistItem, track, likedTrack, likedPlaylist)
            .from(playlist)
            .leftJoin(playlist.likedPlaylists, likedPlaylist)
            .leftJoin(playlist.playlistItems, playlistItem)
            .leftJoin(playlistItem.track, track)
            .where(playlist.id.eq(nowPlayingId))
            .fetch()

        val items = results.map { Item(it.get(playlistItem)!!, it.get(likedTrack) != null) }

        return PlaylistWithLike(
            results[0].get(playlist)!!.id,
            results[0].get(playlist)!!.title,
            results[0].get(likedPlaylist) != null,
            items
        )
    }

    override fun getPlaylistByMember(memberId: Long): List<Main> {
        val results = queryFactory
            .select(playlist, playlistItem, track, likedTrack, likedPlaylist)
            .from(playlist)
            .leftJoin(playlist.likedPlaylists, likedPlaylist)
            .leftJoin(playlist.playlistItems, playlistItem)
            .leftJoin(playlistItem.track, track)
            .where(playlist.member.id.eq(memberId))
            .fetch()

        return listOf()
    }
}