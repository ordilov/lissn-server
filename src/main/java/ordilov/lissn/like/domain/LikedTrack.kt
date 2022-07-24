package ordilov.lissn.like.domain

import ordilov.lissn.member.domain.Member
import ordilov.lissn.track.domain.Track
import javax.persistence.*

@Entity
class LikedTrack(
    @ManyToOne
    @JoinColumn(name = "member_id")
    val member: Member,

    @ManyToOne
    @JoinColumn(name = "track_id")
    val track: Track,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)