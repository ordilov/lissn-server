package ordilov.randomplay.member.interfaces;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.randomplay.common.interfaces.CommonResponse;
import ordilov.randomplay.member.application.MemberFacade;
import ordilov.randomplay.member.domain.Member;
import ordilov.randomplay.member.domain.MemberInfo;
import ordilov.randomplay.member.infrastructure.MemberRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

  private final MemberFacade memberFacade;


  @GetMapping(value = "/{memberId}")
  public CommonResponse<MemberInfo> findMember(@PathVariable String memberId) {
    var memberInfo = memberFacade.getMember(memberId);
    return CommonResponse.success(memberInfo);
  }

  @GetMapping(value = "/{memberId}/playlists")
  public CommonResponse<MemberInfo> findPlaylists(@PathVariable String memberId) {
    var memberInfo = memberFacade.getMember(memberId);
    return CommonResponse.success(memberInfo);
  }

  @PostMapping
  public CommonResponse<MemberDto.RegisterResponse> registerMember(
      @RequestBody @Valid MemberDto.RegisterRequest request) {
    log.info("REQUEST " + request.toString());
    var command = request.toCommand();
    var memberInfo = memberFacade.registerMember(command);
    var response = new MemberDto.RegisterResponse(memberInfo);
    return CommonResponse.success(response);
  }

  @GetMapping(value = "")
  public CommonResponse<String> hello() {
    log.info("hello");

    return CommonResponse.success("hello");
  }


}
