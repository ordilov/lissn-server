package ordilov.randomplay.playlist.domain;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberReader;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistItemDeleteRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.PlaylistUpdateRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeListRequest;
import ordilov.randomplay.playlist.domain.PlaylistCommand.YoutubeVideoRequest;
import ordilov.randomplay.playlist.domain.PlaylistInfo.Main;
import ordilov.randomplay.playlist.domain.PlaylistInfo.PlaylistWithLike;
import ordilov.randomplay.playlist.domain.youtube.YoutubePlaylistItem;
import ordilov.randomplay.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.randomplay.playlist.domain.youtube.YoutubeVideo;
import ordilov.randomplay.playlist.domain.youtube.YoutubeVideo.Item;
import ordilov.randomplay.track.domain.Track;
import ordilov.randomplay.track.domain.TrackStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

  private final PlaylistMapper mapper;
  private final TrackStore trackStore;
  private final MemberReader memberReader;
  private final PlaylistStore playlistStore;
  private final PlaylistReader playlistReader;

  @Override
  public void addPlaylistItem(YoutubeVideoRequest command, YoutubeVideo youtubeVideo) {
    Item item = youtubeVideo.getItems().get(0);
    Track track = trackStore.store(Track.builder().
        title(item.getSnippet().getTitle()).
        resourceId(item.getId()).
        build());
    Playlist playlist = playlistReader.getPlaylistBy(command.getPlaylistId());
    PlaylistItem playlistItem = new PlaylistItem(playlist, track);
    playlist.addPlaylistItem(playlistItem);
  }

  @Override
  public Main addPlaylistItems(YoutubeListRequest command,
      YoutubePlaylistItems youtubePlaylistItems) {
    Playlist playlist = playlistReader.getPlaylistBy(command.getPlaylistId());

    List<PlaylistItem> playlistItems = youtubePlaylistItems.getItems().stream()
        .map(YoutubePlaylistItem::getSnippet)
        .map(snippet -> trackStore.store(
            new Track(snippet.getTitle(), snippet.getResourceId().getVideoId())))
        .map(track -> new PlaylistItem(playlist, track))
        .collect(Collectors.toList());

    playlist.addPlaylistItems(playlistItems);
    return mapper.of(playlist);
  }

  @Override
  public PlaylistInfo.Main createPlaylist(PlaylistCommand.PlaylistCreateRequest command) {
    Member member = memberReader.getMemberBy(command.getMemberId());
    Playlist playlist = playlistStore.store(command.toEntity(member));
    member.createPlaylist(playlist);
    return mapper.of(playlist);
  }

  @Override
  public PlaylistInfo.Main getPlaylistWithLike(Long memberId) {

    PlaylistWithLike playlistWithLikeBy = playlistReader.getPlaylistWithLikeBy(1L, memberId);

    return mapper.of(playlistWithLikeBy.getPlaylist(), playlistWithLikeBy.isLiked());
  }

  @Override
  public List<PlaylistInfo.Main> getPlaylists() {
    return mapper.of(playlistReader.getPlaylists());
  }

  @Override
  public List<PlaylistInfo.Main> getMyPlaylists(Long memberId) {
    List<Playlist> playlists = playlistReader.getPlaylistByMember(memberId);
    return mapper.of(playlists);
  }

  @Override
  public void updatePlaylistTitle(PlaylistUpdateRequest command) {
    playlistStore.update(command.getPlaylistId(), command.getTitle());
  }

  @Override
  public void deletePlaylist(PlaylistDeleteRequest command) {
    playlistStore.delete(command.getPlaylistId());
  }

  @Override
  public void deletePlaylistItem(PlaylistItemDeleteRequest command) {
    playlistStore.deleteItem(command.getPlaylistItemId());
  }

  @Override
  public Main getRandomPlaylist() {
    Playlist playlist = playlistReader.getPlaylistBy(1L);
    return mapper.of(playlist);
  }
}
