package ordilov.randomplay.like.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.playlist.domain.Playlist;
import ordilov.randomplay.track.domain.Track;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class LikedTrack {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "track_id")
  private Track track;

  public LikedTrack(Member member, Track track) {
    this.member = member;
    this.track = track;
    this.track.addLikeCount();
  }
}
