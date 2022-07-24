package ordilov.lissn.track.infrastructure;

import ordilov.lissn.track.domain.Track
import ordilov.lissn.track.domain.TrackStore
import org.springframework.stereotype.Component

@Component
class TrackStoreImpl(
    val trackRepository: TrackRepository
) : TrackStore {

    override fun store(tracks: List<Track>): MutableList<Track> {
        return trackRepository.saveAll(tracks)
    }

    override fun store(track: Track): Track {
        return trackRepository.findByResourceId(track.resourceId)
            .orElse(trackRepository.save(track))
    }
}