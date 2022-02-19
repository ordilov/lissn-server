package ordilov.randomplay.like.domain;

import java.util.Optional;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.playlist.domain.Playlist;
import ordilov.randomplay.track.domain.Track;

public interface LikeReader {

  Optional<LikedTrack> getLikedTrack(Member member, Track track);

  Optional<LikedPlaylist> getLikedPlaylist(Member member, Playlist playlist);
}
