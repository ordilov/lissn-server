package ordilov.randomplay.member.interfaces;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.common.interfaces.CommonResponse;
import ordilov.randomplay.member.application.MemberFacade;
import ordilov.randomplay.member.domain.MemberInfo;
import ordilov.randomplay.security.userinfo.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

  private final MemberFacade memberFacade;

  @GetMapping(value = "/{memberId}")
  public CommonResponse<MemberInfo> findMember(@PathVariable Long memberId) {
    var memberInfo = memberFacade.getMember(memberId);
    return CommonResponse.success(memberInfo);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('USER')")
  public CommonResponse<MemberInfo> getCurrentUser(
      @AuthenticationPrincipal UserPrincipal userPrincipal) {
    var memberInfo = memberFacade.getMember(userPrincipal.getId());
    return CommonResponse.success(memberInfo);
  }

}
