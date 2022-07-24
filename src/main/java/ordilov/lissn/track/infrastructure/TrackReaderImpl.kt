package ordilov.lissn.track.infrastructure;

import ordilov.lissn.track.domain.Track
import ordilov.lissn.track.domain.TrackReader
import org.springframework.stereotype.Component

@Component
class TrackReaderImpl(
    val trackRepository: TrackRepository
) : TrackReader {

    override fun getTrackBy(id: Long): Track {
        return trackRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Track with id $id not found") }
    }

}