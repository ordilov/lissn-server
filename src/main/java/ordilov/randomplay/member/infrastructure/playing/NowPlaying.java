package ordilov.randomplay.member.infrastructure.playing;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class NowPlaying {

  @Id
  private Long id;

  private Long playlistId;

  public NowPlaying(Long id, Long playlistId) {
    this.id = id;
    this.playlistId = playlistId;
  }
}
