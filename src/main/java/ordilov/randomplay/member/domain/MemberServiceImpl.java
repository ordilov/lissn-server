package ordilov.randomplay.member.domain;

import lombok.RequiredArgsConstructor;
import ordilov.randomplay.member.infrastructure.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberStore memberStore;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberInfo login(MemberCommand command) {
        Optional<Member> member = memberRepository.findByEmail(command.getEmail());
        return member.map(MemberInfo::new).orElseGet(() -> new MemberInfo(memberStore.store(command.toEntity())));
    }

    @Override
    public MemberInfo getMemberInfo(String id) {
        return new MemberInfo(memberRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }


}
