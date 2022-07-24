package ordilov.lissn.member.domain.playing

import ordilov.lissn.playlist.domain.Item

data class PlayingInfo(
    val items: List<Item>,
    val nowPlaying: Long
)