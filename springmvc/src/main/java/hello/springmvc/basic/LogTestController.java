package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
// RestController 랑 Controller 의 차이
// Controller 는 변환값이 String 이면 그거를 뷰 이름으로 인식해서, 뷰를 찾고 뷰가 랜더링되는데,
// RestController 는 리턴값의 텍스트를 HTTP 메시지 바디에 바로 입력시켜준다는것
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());
//    이거를 @Slf4j 어노테이션이 자동으로 추가해서 log를 사용할 수 있게 해줌

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

//        System.out.println("name = " + name);

        // log 를 찍을 때 레벨을 나눌 수 있다
        // TRACE > DEBUG > INFO > WARN > ERROR
        // 개발 서버는 debug 출력, 운영 서버는 info 출력
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }

}
