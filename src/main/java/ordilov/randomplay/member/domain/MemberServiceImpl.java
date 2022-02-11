package ordilov.randomplay.member.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberStore memberStore;
  private final MemberReader memberReader;

  @Override
  public MemberInfo login(MemberCommand command) {
    Member member = memberReader.getMemberByEmail(command.getEmail())
        .orElseGet(() -> memberStore.store(command.toEntity()));

    return new MemberInfo(member);
  }

  @Override
  public void updateRefreshToken(Long id, String refreshToken) {
    Member member = memberReader.getMemberBy(id);
    member.setRefreshToken(refreshToken);
  }

  @Override
  @Transactional(readOnly = true)
  public MemberInfo getMemberInfo(Long id) {
    return new MemberInfo(memberReader.getMemberBy(id));
  }
}
