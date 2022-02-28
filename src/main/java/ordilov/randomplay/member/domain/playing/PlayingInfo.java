package ordilov.randomplay.member.domain.playing;

import java.util.List;
import lombok.Getter;
import ordilov.randomplay.playlist.domain.PlaylistInfo.Item;
import ordilov.randomplay.track.domain.TrackInfo;

@Getter
public class PlayingInfo {
  private final List<Item> items;
  private final long nowPlaying;

  public PlayingInfo(List<Item> items, long nowPlaying) {
    this.items = items;
    this.nowPlaying = nowPlaying;
  }
}
