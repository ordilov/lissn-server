package ordilov.lissn.member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.common.adapter.CommonResponse;
import ordilov.lissn.member.application.port.in.UpdateMemberCommand;
import ordilov.lissn.member.domain.MemberInfo.UpdateMemberInfo;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}")
public class UpdateMemberController {

  private final UpdateMemberCommand updateMemberCommand;

  @PatchMapping("/name")
  public CommonResponse<UpdateMemberInfo> updateMember(
      @PathVariable Long memberId,
      @RequestParam String name
  ) {
    return CommonResponse.success(updateMemberCommand.updateName(memberId, name));
  }

  @PatchMapping("/picture")
  public CommonResponse<UpdateMemberInfo> updatePicture(
      @PathVariable Long memberId,
      @RequestParam("data") MultipartFile file) {
    return CommonResponse.success(updateMemberCommand.updatePicture(memberId, file));
  }
}
