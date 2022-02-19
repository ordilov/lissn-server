package ordilov.randomplay.track.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ordilov.randomplay.common.domain.BaseEntity;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Track extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "track_id", nullable = false)
  private Long id;

  @Column(unique = true)
  private String resourceId;
  private String title;

  @ColumnDefault("0")
  private int likeCount = 0;

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
