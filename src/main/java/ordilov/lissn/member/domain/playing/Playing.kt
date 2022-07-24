package ordilov.lissn.member.domain.playing

import ordilov.lissn.common.domain.BaseEntity
import ordilov.lissn.member.domain.Member
import ordilov.lissn.track.domain.Track
import javax.persistence.*

@Entity
class Playing(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    val track: Track,

    @Column(nullable = false, name = "playing_order")
    val order: Long,

    @Id
    @Column(name = "playing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

) : BaseEntity()