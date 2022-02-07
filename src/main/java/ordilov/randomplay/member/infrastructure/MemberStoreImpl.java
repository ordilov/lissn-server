package ordilov.randomplay.member.infrastructure;

import lombok.RequiredArgsConstructor;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberCommand;
import ordilov.randomplay.member.domain.MemberStore;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberStoreImpl implements MemberStore {

  private final MemberRepository memberRepository;

  @Override
  public Member store(Member member) {
    return memberRepository.save(member);
  }

  @Override
  public void update(Member member, MemberCommand command) {
    member.updateProfile(command);
  }
}
