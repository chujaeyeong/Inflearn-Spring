package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
        // 스프링부트에서는 컨트롤러에서 리턴 값으로 문자를 반환하면, 뷰 리졸버 (viewResolver)가 화면을 찾아서 알아서 처리한다
        // 이는 스프링부트 템플릿엔진 기본 매핑
        // return "ViewName" --> resource:templates/ViewName.html 로 연결
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        // RequestParam 안에 required 기본 옵션은 true (무조건 value 값을 넘긴다 안 넘기면 에러 빵) 이라서
        // 안 넘기고 싶으면 require 값을 false로 넣어주면 됨, 이러면 name을 아무값이나 넣어줘도 알아서 넣어줌
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    // ResponseBody 어노테이션을 사용하고 바로 return 하면, view 필요없이, 그리고 template 안에 html 파일 없이도
    // 바로 응답 body 부에 return 내용을 직접 넣어준다는 뜻임
    // 이렇게 쓸 일은 별로 없단다~
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello ~~~~ (~~~~ 는 name 내용)"
    }

    @GetMapping("hello-api")
    @ResponseBody
    // json 형식, ResponseBody는 json 방식으로 반환하는게 기본 (xml로 반환해도 뭐 상관은없음)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // 게터세터 -> 프로퍼티 접근 방식, 자바 빈 기본 규약
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
