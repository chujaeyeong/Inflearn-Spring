package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
// 여기서 컴포넌트 어노테이션으로 스프링 빈에 등록해줘도 되는데, SpringConfig 파일에 따로 빼두는게 좋음.
public class TimeTraceAop {

    // 어라운드는 내가 원하는 컨디션으로 범위를 조정할 수 있다 (보통 패키지 단위로 많이 쓰는 편)
    @Around("execution(* hello.hellospring..*(..))")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
