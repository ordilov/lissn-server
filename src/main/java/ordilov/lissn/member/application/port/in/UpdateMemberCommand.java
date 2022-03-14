package ordilov.lissn.member.application.port.in;

import ordilov.lissn.member.application.port.in.MemberCommand.UpdateCommand;
import ordilov.lissn.member.domain.MemberInfo.UpdateMemberInfo;

public interface UpdateMemberCommand {

  UpdateMemberInfo updateProfile(UpdateCommand command);

  void deleteMember();
}
