package ordilov.lissn.track.domain;

interface TrackService {

    fun addTracks(tracks: List<Track>): List<TrackInfo>
}