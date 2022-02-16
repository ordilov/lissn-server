package ordilov.randomplay.track.domain;

import lombok.Getter;

@Getter
public class TrackInfo {
  private final Long id;
  private final String title;
  private final String resourceId;

  public TrackInfo(Track track) {
    this.id = track.getId();
    this.title = track.getTitle();
    this.resourceId = track.getResourceId();
  }
}
