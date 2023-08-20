package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,
//        권장하는 방법 : 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두면 됨
//        최근 스프링부트도 이 방법을 기본으로 제공함

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))

public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//    수동 빈으로 이름 똑같이 등록해도 수동 빈이 자동 빈보다 우선되기 때문에 테스트 돌리는데는 문제 X (다만 로그가 남는다)
//    근데 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다
//    Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
//    여기서 설정 값을 false로 바꾸면 잘 실행되지만... 그런짓은하지말자... 꼬여서 나중에 잡지도 못 함 
}
