package ordilov.lissn.member.adapter.out.persistence.playing

import ordilov.lissn.common.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class HomePlaying(
    val playlistId: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
) : BaseEntity()