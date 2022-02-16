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
import ordilov.randomplay.playlist.domain.youtube.YoutubePlaylistItem;
import ordilov.randomplay.playlist.domain.youtube.YoutubePlaylistItems;
import ordilov.randomplay.track.domain.Track;
import ordilov.randomplay.track.domain.TrackStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

  private final PlaylistStore playlistStore;
  private final PlaylistReader playlistReader;
  private final MemberReader memberReader;
  private final PlaylistInfoMapper mapper;
  private final TrackStore trackStore;

  @Override
  public void addPlaylistItem(YoutubeVideoRequest command, String videoId) {
//    Track track = trackStore.store(new Track());
    PlaylistItem playlistItem = new PlaylistItem();
  }

  @Override
  public Main addPlaylistItems(YoutubeListRequest command, YoutubePlaylistItems youtubePlaylistItems) {
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
  public PlaylistInfo.Main createPlaylist(PlaylistCommand command) {
    Member member = memberReader.getMemberBy(command.getMemberId());
    Playlist playlist = playlistStore.store(command.toEntity(member));
    member.createPlaylist(playlist);
    return mapper.of(playlist);
  }

  @Override
  public PlaylistInfo.Main getPlaylist(Long id) {
    return mapper.of(playlistReader.getPlaylistBy(id));
  }

  @Override
  public List<PlaylistInfo.Main> getPlaylists() {
    return mapper.of(playlistReader.getPlaylists());
  }

  @Override
  public List<PlaylistInfo.Main> getMyPlaylists(Long memberId) {
    List<Playlist> playlists = playlistReader.getPlaylistByMember(memberId);
    log.info("playlist size {}", playlists.size());

    return mapper.of(playlists);
  }

  @Override
  public void updatePlaylistTitle(PlaylistUpdateRequest command) {

  }

  @Override
  public void deletePlaylist(PlaylistDeleteRequest command) {
    playlistStore.delete(command.getPlaylistId());
  }

  @Override
  public void deletePlaylistItem(PlaylistItemDeleteRequest command) {
    playlistStore.deleteItem(command.getPlaylistItemId());
  }
}
