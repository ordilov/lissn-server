package ordilov.randomplay.like.domain;

public interface LikeStore {
  LikedTrack store(LikedTrack likedTrack);
  LikedPlaylist store(LikedPlaylist likedPlaylist);
  void delete(LikedTrack likedTrack);
  void delete(LikedPlaylist likedPlaylist);
}
