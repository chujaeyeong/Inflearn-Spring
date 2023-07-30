package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // DI에는 필드 주입, setter 주입, 생성자 주입 이렇게 3가지 방법이 있는데,
    // 가장 많이 쓰는 방법은 생성자 주입 (내가 항상 쓰던거)
    // 필드 주입은 중간에 못바꿔서 잘 안 씀
    // setter 주입은 public으로 열어놓고 주입해야되서 밖에서 다른 곳에서 사용할 수 있다
    // 암튼 의존관계가 실행 중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장하고, 실제도 생성자 주입을 제일 많이 씀.

    // 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다 (오토와이어드, 서비스 어노테이션으로 자동 스캔해서 의존관계 주입하는방식)
    // 그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
    
}
