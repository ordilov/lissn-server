package ordilov.lissn.member.application.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ordilov.lissn.member.application.port.in.GetMemberQuery;
import ordilov.lissn.member.application.port.out.MemberReader;
import ordilov.lissn.member.domain.MemberInfo.GetMemberInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMemberService implements GetMemberQuery {

  private final MemberReader memberReader;

  @Override
  public GetMemberInfo getMember(Long memberId) {
    return new GetMemberInfo(memberReader.getMemberBy(memberId));
  }

  @Override
  public Optional<GetMemberInfo> getMemberByEmail(String email) {
    return memberReader.getMemberByEmail(email).map(GetMemberInfo::new);
  }
}
