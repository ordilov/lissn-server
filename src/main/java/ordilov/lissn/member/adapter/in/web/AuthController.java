package ordilov.lissn.member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.common.adapter.CommonResponse;
import ordilov.lissn.member.adapter.in.web.AuthDto.RefreshTokenDto;
import ordilov.lissn.member.application.port.in.AuthCommand;
import ordilov.lissn.member.domain.AuthInfo.RefreshInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthCommand authCommand;

  @PostMapping("/refresh")
  public CommonResponse<RefreshInfo> refresh(@RequestBody RefreshTokenDto refreshTokenDto) {
    RefreshInfo refreshInfo = authCommand.refresh(refreshTokenDto.getRefreshToken());
    return CommonResponse.success(refreshInfo);
  }

}
