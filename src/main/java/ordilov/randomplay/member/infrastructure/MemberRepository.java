package ordilov.randomplay.member.infrastructure;

import ordilov.randomplay.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
}
