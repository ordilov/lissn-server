package ordilov.lissn.like.domain;

import java.util.Optional;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.playlist.domain.Playlist;
import ordilov.lissn.track.domain.Track;

public interface LikeReader {

  Optional<LikedTrack> getLikedTrack(Member member, Track track);

  Optional<LikedPlaylist> getLikedPlaylist(Member member, Playlist playlist);
}
