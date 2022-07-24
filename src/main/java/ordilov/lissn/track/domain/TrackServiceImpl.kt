package ordilov.lissn.track.domain;

import org.springframework.stereotype.Service

@Service
class TrackServiceImpl(
    val trackStore: TrackStore,
    val trackReader: TrackReader
) : TrackService {

    override fun addTracks(tracks: List<Track>): List<TrackInfo> {
        return trackStore.store(tracks)
            .map { TrackInfo(it.id!!, it.title, it.resourceId) }.toList()
    }
}