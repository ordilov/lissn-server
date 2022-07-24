package ordilov.lissn.member.domain.playing

import ordilov.lissn.member.adapter.out.persistence.playing.PlayingRepository
import org.springframework.stereotype.Repository

@Repository
class PlayingReaderImpl(
    val playingRepository: PlayingRepository
) : PlayingReader {
    override fun getPlayingByMember(memberId: Long): PlayingInfo {
        return PlayingInfo(listOf(), 0L)
    }
}