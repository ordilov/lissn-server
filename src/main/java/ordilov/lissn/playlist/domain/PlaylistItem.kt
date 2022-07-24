package ordilov.lissn.playlist.domain;

import ordilov.lissn.common.domain.BaseEntity
import ordilov.lissn.track.domain.Track
import javax.persistence.*

@Entity
data class PlaylistItem(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    val playlist: Playlist,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    val track: Track,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)