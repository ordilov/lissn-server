package ordilov.randomplay.playlist.domain;

import java.util.List;
import ordilov.randomplay.playlist.domain.PlaylistInfo.Item;
import ordilov.randomplay.playlist.domain.PlaylistInfo.PlaylistWithLike;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

  @Mappings({
      @Mapping(expression = "java(playlist.getPlaylistItems().stream().map(p -> of(p)).collect(java.util.stream.Collectors.toList()))", target = "items")
  })
  PlaylistInfo.Main of(Playlist playlist);

  PlaylistInfo.Main of(PlaylistWithLike playlistWithLike);

  @Mappings({
      @Mapping(expression = "java(item.getTrack().getId())", target = "trackId"),
      @Mapping(expression = "java(item.getTrack().getTitle())", target = "title"),
      @Mapping(expression = "java(item.getTrack().getResourceId())", target = "resourceId"),
  })
  Item of(PlaylistItem item);

  List<PlaylistInfo.Main> of(List<PlaylistWithLike> playlists);
}
