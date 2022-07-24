package ordilov.lissn.member.domain;

import ordilov.lissn.common.domain.BaseEntity
import ordilov.lissn.member.domain.playing.Playing
import ordilov.lissn.playlist.domain.Playlist
import org.hibernate.annotations.ColumnDefault
import org.springframework.boot.context.properties.bind.DefaultValue
import javax.persistence.*
import javax.validation.constraints.Email


@Entity
@Table(name = "member")
data class Member(
    @Column(length = 60, nullable = false)
    var name: String = "",

    @Email
    @Column(nullable = false, unique = true, length = 250)
    var email: String = "",

    @Column(length = 250)
    var picture: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val provider: AuthProvider = AuthProvider.google,
) : BaseEntity() {

    @Column(length = 250)
    var refreshToken: String = ""

    var isDeleted: Boolean = false

    @ColumnDefault("0")
    @Column(nullable = true)
    var nowPlaying: Long = 0L

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    val playlists: MutableList<Playlist> = mutableListOf()

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    val playings: MutableList<Playing> = mutableListOf()

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun updateName(name: String) {
        this.name = name
    }

    fun updatePicture(picture: String) {
        this.picture = picture;
    }

    fun playTrack(playOrder: Long) {
        this.nowPlaying = playOrder;
    }


    fun createPlaylist(playlist: Playlist) {
        this.playlists.add(playlist)
    }
}