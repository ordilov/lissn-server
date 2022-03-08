package ordilov.lissn.track.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ordilov.lissn.common.domain.BaseEntity;
import ordilov.lissn.like.domain.LikedTrack;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Track extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "track_id", nullable = false)
  private Long id;

  @Column(unique = true, length = 200)
  private String resourceId;
  private String title;

  @ColumnDefault("0")
  private int likeCount = 0;

  @OneToMany(mappedBy = "track", fetch = LAZY)
  private final List<LikedTrack> likedTracks = new ArrayList<>();

  @Builder
  public Track(String title, String resourceId) {
    this.title = title;
    this.resourceId = resourceId;
  }

  public void addLikeCount(){
    likeCount++;
  }

  public void removeLikeCount(){
    if(likeCount <= 0) return;
    likeCount--;
  }
}
