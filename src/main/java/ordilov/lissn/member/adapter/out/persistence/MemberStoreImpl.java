package ordilov.lissn.member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.member.application.port.in.MemberCommand;
import ordilov.lissn.member.application.port.out.MemberStore;
import ordilov.lissn.member.domain.Member;
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
    member.updateProfile(command.getName(), command.getProfileImageUrl());
  }
}
