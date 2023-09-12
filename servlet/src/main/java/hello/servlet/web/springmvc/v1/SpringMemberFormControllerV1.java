package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// 컨트롤러 어노테이션 안에 컴포넌트도 들어있어서 자동으로 컴포넌트 스캔에 대상이 된다 = @Compornent + @RequestMapping (클래스 레벨에서도 적어줘야됨)
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }

}
