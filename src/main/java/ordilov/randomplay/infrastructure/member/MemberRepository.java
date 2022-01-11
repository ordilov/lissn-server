package ordilov.randomplay.infrastructure.member;

import ordilov.randomplay.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
