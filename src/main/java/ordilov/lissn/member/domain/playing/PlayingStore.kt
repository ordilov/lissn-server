package ordilov.lissn.member.domain.playing

interface PlayingStore {

    fun clear(memberId: Long)

    fun store(playing: Playing): Playing

    fun store(playings: List<Playing>): List<Playing>

    fun delete(playingId: Long)
}