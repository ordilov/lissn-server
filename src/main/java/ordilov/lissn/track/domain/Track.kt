package ordilov.lissn.track.domain;

import ordilov.lissn.common.domain.BaseEntity
import ordilov.lissn.like.domain.LikedTrack
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Entity
class Track(
    @Column(unique = true, length = 200)
    val resourceId: String,

    val title: String,

    @ColumnDefault("0")
    var likeCount: Int = 0,

    @OneToMany(mappedBy = "track", fetch = FetchType.LAZY)
    val likes: MutableList<LikedTrack> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id", nullable = false)
    var id: Long = 0,
) : BaseEntity() {


    fun addLikeCount() {
        likeCount++
    }

    fun removeLikeCount() {
        if (likeCount <= 0) return
        likeCount--
    }
}