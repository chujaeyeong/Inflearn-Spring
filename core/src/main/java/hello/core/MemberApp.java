package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    // 애플리케이션 로직으로 이렇게 테스트 하는 것은 좋은 방법은 아님
    // JUnit 테스트를 사용하자!
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP); // id 가 Long 타입이라 1L로 안 하면 (1로만 하면 컴파일오류남)
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }


}
