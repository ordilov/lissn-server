package ordilov.lissn.member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.common.interfaces.CommonResponse;
import ordilov.lissn.member.application.port.in.GetMemberQuery;
import ordilov.lissn.member.domain.MemberInfo;
import ordilov.lissn.security.userinfo.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class GetMemberController {

  private final GetMemberQuery getMemberQuery;

  @GetMapping(value = "/{memberId}")
  public CommonResponse<MemberInfo> findMember(@PathVariable Long memberId) {
    var memberInfo = getMemberQuery.getMember(memberId);
    return CommonResponse.success(memberInfo);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('USER')")
  public CommonResponse<MemberInfo> getCurrentMember(
      @AuthenticationPrincipal UserPrincipal userPrincipal) {
    var memberInfo = getMemberQuery.getMember(userPrincipal.getId());
    return CommonResponse.success(memberInfo);
  }
}
