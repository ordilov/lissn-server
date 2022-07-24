package ordilov.lissn.member.adapter.out.persistence.playing

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class PlayingTasks(
    val homePlayingRepository: HomePlayingRepository,
) {

    val NOW_PLAYING_ID: Long = 1L

    @Scheduled(cron = "*/10 * * * * *")
    fun reportCurrentTime() {
        val nowPlayingOptional = homePlayingRepository.findById(NOW_PLAYING_ID)
        val nowPlaying = nowPlayingOptional.map { it.playlistId }.orElse(1L)
        nowPlayingOptional.ifPresent(homePlayingRepository::delete)

        val rand = getRandom(nowPlaying)
        homePlayingRepository.save(HomePlaying(NOW_PLAYING_ID, rand))
    }

    fun getRandom(nowPlaying: Long): Long {
        var rand = nowPlaying
        while (rand == nowPlaying) {
            rand = ((Math.random() * 4) + 1).toLong()
        }
        return rand
    }
}