package ordilov.randomplay.like.domain;

import ordilov.randomplay.playlist.domain.Playlist;

public interface LikeService {
    void like(LikeCommand.LikePlaylistRequest request);
    void like(LikeCommand.LikeTrackRequest request);
    void unlike(Long memberId, Long playlistId);
    boolean isLiked(Long memberId, Long playlistId);
}
