package ordilov.lissn.member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.member.application.port.in.MemberCommand.UpdateCommand;
import ordilov.lissn.member.application.port.out.MemberStore;
import ordilov.lissn.member.domain.Member;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberStoreImpl implements MemberStore {

  private final MemberRepository memberRepository;

  @Override
  public Member store(Member member) {
    return memberRepository.save(member);
  }

  @Override
  public Member update(UpdateCommand command) {
    Member member = memberRepository.
        findById(command.getId()).orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
    member.updateProfile(command.getName(), command.getPicture());
    log.info("member: {}", member);
    return member;
  }
}
