package ordilov.lissn.member.domain.playing

interface PlayingReader {

    fun getPlayingByMember(memberId: Long): PlayingInfo
}