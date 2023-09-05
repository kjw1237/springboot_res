package kr.co.team.res.controller;

import kr.co.team.res.common.Base;
import kr.co.team.res.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController extends Base {

    @RequestMapping("/")
    public String index(Model model){
        //commit test
        MemberVO vo = new MemberVO();

        vo.setLoginId("testLoginId");
        vo.setPwd("testPwd");

        model.addAttribute("data" , vo);
        return "index";

    }

}
