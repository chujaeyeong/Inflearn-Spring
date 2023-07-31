package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 왜 정적 리소스에 index.html 을 안 열고 home.html 을 열어주냐면~
    // 먼저 열어주는 페이지에는 우선순위가 있는데, 스프링이 먼저 컨트롤러에서 "/" 으로 매핑된 파일을 찾기 때문에
    // home.html이 우선되는거고, static에 넣어둔 index.html은 후순위로 밀리는거랍니다~
    // 결론 : 컨트롤러가 정적 파일보다 우선순위가 높다!

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
