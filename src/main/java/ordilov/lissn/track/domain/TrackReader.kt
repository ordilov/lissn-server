package ordilov.lissn.track.domain;

interface TrackReader {
    fun getTrackBy(id: Long): Track
}