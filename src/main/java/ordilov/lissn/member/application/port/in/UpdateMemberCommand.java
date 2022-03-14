package ordilov.lissn.member.application.port.in;

import ordilov.lissn.member.domain.MemberInfo.UpdateMemberInfo;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateMemberCommand {

  UpdateMemberInfo updateName(Long id, String name);

  UpdateMemberInfo updatePicture(Long id, MultipartFile imageFile);

  void deleteMember();
}
