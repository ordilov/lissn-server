package ordilov.randomplay.playlist.domain;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PlaylistInfoMapper {

  PlaylistInfo.Main of(Playlist playlist);

  @Mappings({
      @Mapping(expression = "java(item.getTrack().getTitle())", target = "title"),
      @Mapping(expression = "java(item.getTrack().getResourceId())", target = "resourceId"),
  })
  PlaylistInfo.PlaylistItem of(PlaylistItem item);

  List<PlaylistInfo.Main> of(List<Playlist> playlists);
}
