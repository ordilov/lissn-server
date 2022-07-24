package ordilov.lissn.track.domain;

interface TrackStore {

    fun store(track: Track): Track

    fun store(tracks: List<Track>): List<Track>
}