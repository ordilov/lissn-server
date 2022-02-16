package ordilov.randomplay.playlist.domain.youtube;

import lombok.Getter;

@Getter
public class YoutubePlaylists {

  String kind;
  String etag;
  String id;
  String snippet;
  Status status;
  ContentDetails contentDetails;
  Player player;
  String[] tags;

  @Getter
  private static class Status {
    String privacyStatus;
  }

  @Getter
  private static class ContentDetails {

    int itemCount;
  }

  @Getter
  private static class Player {

    String embedHtml;
  }
}
