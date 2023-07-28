package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello (Model model) {
        model.addAttribute("data","spring!!");
        return "hello";
        // 스프링부트에서는 컨트롤러에서 리턴 값으로 문자를 반환하면, 뷰 리졸버 (viewResolver)가 화면을 찾아서 알아서 처리한다
        // 이는 스프링부트 템플릿엔진 기본 매핑
        // return "ViewName" --> resource:templates/ViewName.html 로 연결
    }
}
