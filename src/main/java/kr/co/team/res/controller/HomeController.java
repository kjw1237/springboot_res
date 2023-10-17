package kr.co.team.res.controller;

import kr.co.team.res.common.Base;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController extends Base {

    @RequestMapping("/")
    public String index(HttpSession session) {

        if(session.getAttribute("user") == null) {
            return "pages/member/login";
        } else {
            return "index";
        }
    }

    @RequestMapping("/common")
    public String common(){

        return "pages/common/commonpage";
    }
}
