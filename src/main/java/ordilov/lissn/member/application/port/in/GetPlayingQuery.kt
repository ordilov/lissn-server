package ordilov.lissn.member.application.port.`in`

import ordilov.lissn.member.domain.playing.PlayingInfo;

interface GetPlayingQuery {
    fun getPlayingInfo(memberId: Long): PlayingInfo
}