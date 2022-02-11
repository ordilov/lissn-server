package ordilov.randomplay.member.domain;

import static javax.persistence.FetchType.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ordilov.randomplay.common.domain.BaseEntity;
import ordilov.randomplay.playlist.domain.Playlist;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

  @Id
  @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 60, nullable = false)
  private String name;

  @Email
  @Column(nullable = false, unique = true, length = 250)
  private String email;
  private String refreshToken;
  private String profileImageUrl;

  @Enumerated(EnumType.STRING)
  private AuthProvider provider;
  private Boolean isDeleted = false;

  @OneToMany(mappedBy = "member", fetch = LAZY)
  private final List<Playlist> playlists = new ArrayList<>();

  @Builder
  public Member(String name, String email, String refreshToken, String profileImageUrl, AuthProvider provider) {
    this.name = name;
    this.email = email;
    this.refreshToken = refreshToken;
    this.profileImageUrl = profileImageUrl;
    this.provider = provider;
  }

  public void updateProfile(MemberCommand command) {
    this.name = command.getName();
    this.profileImageUrl = command.getProfileImageUrl();
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void createPlaylist(Playlist playlist) {
    this.playlists.add(playlist);
  }
}
