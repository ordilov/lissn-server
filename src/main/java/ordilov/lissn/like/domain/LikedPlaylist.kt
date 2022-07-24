package ordilov.lissn.like.domain

import ordilov.lissn.member.domain.Member
import ordilov.lissn.playlist.domain.Playlist
import javax.persistence.*

@Entity
class LikedPlaylist(
    @ManyToOne
    @JoinColumn(name = "member_id")
    val member: Member? = null,

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    val playlist: Playlist? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

}