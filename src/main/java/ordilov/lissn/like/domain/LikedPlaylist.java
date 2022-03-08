package ordilov.lissn.like.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.playlist.domain.Playlist;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class LikedPlaylist {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "playlist_id")
  private Playlist playlist;

  public LikedPlaylist(Member member, Playlist playlist) {
    this.member = member;
    this.playlist = playlist;
    playlist.addLikeCount();
  }
}
