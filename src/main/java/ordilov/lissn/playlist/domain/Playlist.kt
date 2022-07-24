package ordilov.lissn.playlist.domain

import ordilov.lissn.common.domain.BaseEntity
import ordilov.lissn.like.domain.LikedPlaylist
import ordilov.lissn.member.domain.Member
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Entity
class Playlist(
    @Column(name = "title")
    var title: String,

    @ManyToOne
    @JoinColumn(name = "member_id")
    val member: Member,

    @ColumnDefault("0")
    var likeCount: Int = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    var type: PlaylistType = PlaylistType.CUSTOM,

    @OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val playlistItems: MutableList<PlaylistItem> = mutableListOf(),

    @OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY)
    val likedPlaylists: MutableList<LikedPlaylist> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    val id: Long? = null

) : BaseEntity() {

    constructor(title: String, member: Member) : this(title, member, 0, PlaylistType.CUSTOM)

    fun updateTitle(title: String) {
        this.title = title
    }

    fun addPlaylistItem(playlistItem: PlaylistItem) {
        playlistItems.add(playlistItem)
    }

    fun addPlaylistItems(playlistItems: List<PlaylistItem>) {
        this.playlistItems.addAll(playlistItems)
    }

    fun increaseLikeCount() {
        likeCount++
    }

    fun decreaseLikeCount() {
        if (likeCount <= 0) {
            return
        }
        likeCount--
    }
}