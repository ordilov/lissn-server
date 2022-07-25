package ordilov.lissn.member.application.service

import ordilov.lissn.member.application.port.out.MemberReader
import ordilov.lissn.member.application.port.`in`.PlayCommand
import ordilov.lissn.member.domain.playing.Playing
import ordilov.lissn.member.domain.playing.PlayingPlaylistDto
import ordilov.lissn.member.domain.playing.PlayingReader
import ordilov.lissn.member.domain.playing.PlayingStore
import ordilov.lissn.playlist.application.port.out.PlaylistReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.IntStream

@Service
@Transactional
abstract class UpdatePlayingService(
    val memberReader: MemberReader,
    val playingStore: PlayingStore,
    val playingReader: PlayingReader,
    val playlistReader: PlaylistReader,
) : PlayCommand {

    override fun addPlaying(command: PlayingPlaylistDto) {
        val member = memberReader.getMemberBy(command.memberId)
        val playlist = playlistReader.getPlaylistBy(command.playlistId)

        val index = AtomicLong()
        val playings = playlist.playlistItems
            .map { it.track }
            .map { track ->
                playingStore.store(
                    Playing(
                        member,
                        track,
                        index.getAndIncrement()
                    )
                )
            }

        val selected = IntStream.range(0, playlist.playlistItems.size)
            .filter { i -> playlist.playlistItems[i].id == command.playlistId }
            .findFirst().orElse(0).toLong()
        member.playTrack(selected)
    }

    override fun changePlaying(playingCommand: PlayingPlaylistDto) {
        playingStore.clear(playingCommand.memberId)
        addPlaying(playingCommand)
    }

    override fun deletePlaying(memberId: Long, playingId: Long) {
        val member = memberReader.getMemberBy(memberId)
        var playing = member.nowPlaying
        if (playing != 0L) {
            member.playTrack(--playing)
        }
        playingStore.delete(playingId)
    }

    override fun playTrack(memberId: Long, trackId: Long) {
        val member = memberReader.getMemberBy(memberId)
        member.playTrack(trackId)
    }

}