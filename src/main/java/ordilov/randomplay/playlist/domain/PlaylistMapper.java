package ordilov.randomplay.playlist.domain;

import java.util.List;
import ordilov.randomplay.playlist.domain.PlaylistInfo.PlaylistWithLike;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

  PlaylistInfo.Main of(Playlist playlist);

  PlaylistInfo.Main of(Playlist playlist, boolean isLiked);

  @Mappings({
      @Mapping(expression = "java(playlistWithLike.isLiked())", target = "isLiked"),
  })
  PlaylistInfo.Main of(PlaylistWithLike playlistWithLike);

  @Mappings({
      @Mapping(expression = "java(item.getTrack().getId())", target = "trackId"),
      @Mapping(expression = "java(item.getTrack().getTitle())", target = "title"),
      @Mapping(expression = "java(item.getTrack().getResourceId())", target = "resourceId"),
  })
  PlaylistInfo.PlaylistItem of(PlaylistItem item);

  List<PlaylistInfo.Main> of(List<Playlist> playlists);


}
