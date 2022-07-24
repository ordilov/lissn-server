package ordilov.lissn.member.adapter.out.persistence.playing;

import ordilov.lissn.member.domain.playing.Playing
import ordilov.lissn.member.domain.playing.PlayingStore
import org.springframework.stereotype.Repository

@Repository
class PlayingStoreImpl(
    val playingRepository: PlayingRepository
) : PlayingStore {

    override fun clear(memberId: Long) {
        playingRepository.deleteByMemberId(memberId)
    }

    override fun store(playing: Playing): Playing {
        return playingRepository.save(playing)
    }

    override fun store(playings: List<Playing>): List<Playing> {
        return playingRepository.saveAll(playings)
    }

    override fun delete(playingId: Long) {
        playingRepository.deleteById(playingId)
    }
}