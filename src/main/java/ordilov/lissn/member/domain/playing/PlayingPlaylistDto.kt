package ordilov.lissn.member.domain.playing;

data class PlayingPlaylistDto(
    val memberId: Long,
    val playlistId: Long,
    val playlistItemId: Long
) {

}