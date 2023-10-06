package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.Base;
import kr.co.team.res.domain.vo.MemberVO;
import kr.co.team.res.service.web.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.desktop.SystemEventListener;

@Controller
@RequiredArgsConstructor
public class LoginController extends Base {

    private final MemberService memberService;

    @RequestMapping("/login")
    public String loginpage() {
        return "pages/member/login";
    }

    @GetMapping("/logout")
    public String logout(){

        return "pages/member/login";
    }


    @PostMapping("/api/member/login")
    public String memberLogin(MemberVO memberVO){

        return "/index";
    }
}
