package ordilov.randomplay.playlist.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ordilov.randomplay.common.domain.BaseEntity;
import ordilov.randomplay.member.domain.Member;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@RequiredArgsConstructor(access = PROTECTED)
public class Playlist extends BaseEntity {

  @OneToMany(mappedBy = "playlist", fetch = LAZY, cascade = CascadeType.ALL)
  private final List<PlaylistItem> playlistItems = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String title;
  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ColumnDefault("0")
  private int likeCount = 0;

  @Builder
  public Playlist(String title, Member member) {
    this.title = title;
    this.member = member;
  }

  public void updateTitle(String title) {
    this.title = title;
  }

  public void addPlaylistItem(PlaylistItem playlistItem) {
    playlistItems.add(playlistItem);
  }

  public void addPlaylistItems(List<PlaylistItem> playlistItems) {
    this.playlistItems.addAll(playlistItems);
  }

  public void addLikeCount() {
    likeCount++;
  }

  public void removeLikeCount() {
    likeCount--;
  }

}
