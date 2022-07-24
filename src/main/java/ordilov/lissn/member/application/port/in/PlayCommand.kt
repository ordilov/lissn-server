package ordilov.lissn.member.application.port.`in`

import ordilov.lissn.member.domain.playing.PlayingPlaylistDto;

interface PlayCommand {
    fun addPlaying(command: PlayingPlaylistDto)

    fun deletePlaying(memberId: Long, playingId: Long)

    fun playTrack(memberId: Long, playingId: Long)

    fun changePlaying(playingCommand: PlayingPlaylistDto)
}