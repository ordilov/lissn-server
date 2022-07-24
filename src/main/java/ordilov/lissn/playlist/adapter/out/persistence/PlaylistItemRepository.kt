package ordilov.lissn.playlist.adapter.out.persistence;

import ordilov.lissn.playlist.domain.PlaylistItem;
import org.springframework.data.jpa.repository.JpaRepository;

interface PlaylistItemRepository : JpaRepository<PlaylistItem, Long>