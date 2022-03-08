package ordilov.lissn.member.domain.playing;

import java.util.List;
import lombok.Getter;
import ordilov.lissn.playlist.domain.PlaylistInfo.Item;

@Getter
public class PlayingInfo {
  private final List<Item> items;
  private final long nowPlaying;

  public PlayingInfo(List<Item> items, long nowPlaying) {
    this.items = items;
    this.nowPlaying = nowPlaying;
  }
}
