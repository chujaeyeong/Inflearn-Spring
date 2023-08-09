package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 세미콜론 치기 귀찮다면... 커멘드 + 쉬프트 + 엔터 로 자동완성 시키자
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
