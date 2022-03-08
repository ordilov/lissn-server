package ordilov.lissn.member.adapter.out.persistence;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ordilov.lissn.member.application.port.out.MemberReader;
import ordilov.lissn.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberReaderImpl implements MemberReader {

  private final MemberRepository memberRepository;

  @Override
  public Member getMemberBy(Long id) {
    return memberRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
  }

  @Override
  public Optional<Member> getMemberByEmail(String email) {
    return memberRepository.findByEmail(email);
  }
}
