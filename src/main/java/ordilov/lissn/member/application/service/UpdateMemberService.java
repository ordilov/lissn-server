package ordilov.lissn.member.application.service;

import lombok.RequiredArgsConstructor;
import ordilov.lissn.common.application.port.out.UploadImageHandler;
import ordilov.lissn.member.application.port.in.MemberCommand.UpdateCommand;
import ordilov.lissn.member.application.port.in.UpdateMemberCommand;
import ordilov.lissn.member.application.port.out.MemberStore;
import ordilov.lissn.member.domain.AuthInfo.TokenInfo;
import ordilov.lissn.member.domain.Member;
import ordilov.lissn.member.domain.MemberInfo.UpdateMemberInfo;
import ordilov.lissn.security.TokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateMemberService implements UpdateMemberCommand {

  private final MemberStore memberStore;
  private final TokenProvider tokenProvider;
  private final UploadImageHandler uploadImageHandler;

  @Override
  public UpdateMemberInfo updateName(Long id, String name) {
    Member member = memberStore.updateName(id, name);
    TokenInfo tokenInfo = new TokenInfo(member);
    String accessToken = tokenProvider.createToken(tokenInfo);
    String newRefreshToken = tokenProvider.createRefreshToken(tokenInfo);
    return new UpdateMemberInfo(member, accessToken, newRefreshToken);
  }

  @Override
  public UpdateMemberInfo updatePicture(Long id, MultipartFile imageFile) {
    String picture = uploadImageHandler.upload(id, imageFile);
    Member member = memberStore.updatePicture(id, picture);
    TokenInfo tokenInfo = new TokenInfo(member);
    String accessToken = tokenProvider.createToken(tokenInfo);
    String newRefreshToken = tokenProvider.createRefreshToken(tokenInfo);
    return new UpdateMemberInfo(member, accessToken, newRefreshToken);
  }

  @Override
  public void deleteMember() {

  }
}
