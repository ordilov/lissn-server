package ordilov.lissn.member.application.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ordilov.lissn.member.application.port.in.GetMemberQuery;
import ordilov.lissn.member.application.port.out.MemberReader;
import ordilov.lissn.member.domain.MemberInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMemberService implements GetMemberQuery {

  private final MemberReader memberReader;

  @Override
  public MemberInfo getMember(Long memberId) {
    return new MemberInfo(memberReader.getMemberBy(memberId));
  }

  @Override
  public Optional<MemberInfo> getMemberByEmail(String email) {
    return memberReader.getMemberByEmail(email).map(MemberInfo::new);
  }
}
