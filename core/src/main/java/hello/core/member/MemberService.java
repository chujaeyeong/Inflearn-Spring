package hello.core.member;

public interface MemberService {

    // 회원가입, 회원조회 두 가지 기능을 만들 것이다

    void join(Member member);

    Member findMember(Long memberId);

}
