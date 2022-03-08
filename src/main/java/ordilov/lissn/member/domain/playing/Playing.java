package ordilov.lissn.member.domain.playing;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ordilov.lissn.common.domain.BaseEntity;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.track.domain.Track;

@Getter
@Entity
@RequiredArgsConstructor(access = PROTECTED)
public class Playing extends BaseEntity {

  @Id
  @Column(name = "playing_id")
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "track_id")
  private Track track;

  @Column(nullable = false, name = "playing_order")
  private Long order;

  @Builder
  public Playing(Member member, Track track, Long order) {
    this.member = member;
    this.track = track;
    this.order = order;
  }
}
