package ordilov.lissn.member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.common.interfaces.CommonResponse;
import ordilov.lissn.member.adapter.in.web.MemberDto.UpdateRequest;
import ordilov.lissn.member.application.port.in.MemberCommand.UpdateCommand;
import ordilov.lissn.member.application.port.in.UpdateMemberCommand;
import ordilov.lissn.member.domain.MemberInfo.UpdateMemberInfo;
import ordilov.lissn.security.userinfo.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class UpdateMemberController {

  private final UpdateMemberCommand updateMemberCommand;

  @PatchMapping
  public CommonResponse<UpdateMemberInfo> updateMember(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestBody UpdateRequest request) {
    UpdateCommand updateCommand = new UpdateCommand(
        userPrincipal.getId(),
        request.getName(),
        request.getPicture());
    return CommonResponse.success(updateMemberCommand.updateProfile(updateCommand));
  }
}
