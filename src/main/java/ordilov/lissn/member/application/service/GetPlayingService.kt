package ordilov.lissn.member.application.service

import ordilov.lissn.member.application.port.`in`.GetPlayingQuery
import ordilov.lissn.member.domain.playing.PlayingInfo
import ordilov.lissn.member.domain.playing.PlayingReader
import org.springframework.stereotype.Service

@Service
class GetPlayingService(
    val playingReader: PlayingReader
) : GetPlayingQuery {

    override fun getPlayingInfo(memberId: Long): PlayingInfo {
        return playingReader.getPlayingByMember(memberId)
    }
}