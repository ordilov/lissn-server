package ordilov.lissn.track.infrastructure;

import ordilov.lissn.track.domain.Track
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TrackRepository : JpaRepository<Track, Long> {
    fun findByResourceId(resourceId: String): Optional<Track>
}