package ordilov.randomplay.member.infrastructure;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberReader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberReaderImpl implements MemberReader {

  private final MemberRepository memberRepository;

  @Override
  public Optional<Member> getMemberBy(String id) {
    return memberRepository.findById(id);
  }

  @Override
  public Optional<Member> getMemberByEmail(String email) {
    return memberRepository.findByEmail(email);
  }
}
