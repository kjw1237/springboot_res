package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.Base;
import kr.co.team.res.domain.vo.MemberVO;
import kr.co.team.res.domain.vo.StoreVO;
import kr.co.team.res.service.web.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import javax.swing.text.Style;
import java.awt.desktop.SystemEventListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@RequiredArgsConstructor
public class MemberController extends Base{
    private final MemberService memberService;

    @RequestMapping("/member/register")
    public String registerpage() { return "pages/member/register"; }

    @RequestMapping("/member/login")
    public String loginpage() {
        return "pages/member/login";
    }
    @GetMapping("api/member/login")
    public String memberLogin(MemberVO memberVO){


        return "/index";
    }


    @PostMapping("/api/member/memberInsert")
    public String memberInsert(MemberVO memberVo,
                                  StoreVO storeVo) {

         boolean result = memberService.memberInsert(memberVo , storeVo);

        String month = "";
        String day = "";

        if(memberVo.getMonth().length() == 1){
            month = "0"+memberVo.getMonth();
        } else {
            month = memberVo.getMonth();
        }

        if(memberVo.getDay().length() == 1){
            day = "0"+memberVo.getDay();
        } else {
            day = memberVo.getDay();
        }

        LocalDate date = LocalDate.parse(memberVo.getYear() +"-"+ month +"-"+ day, DateTimeFormatter.ISO_DATE);






        if(result) {
            return "pages/member/register_success";
        } else {
            return "pages/member/register";
        }
    }

    @PostMapping("/api/member/verifyduplicateloginid")
    @ResponseBody
    public boolean verifyDuplicateLoginId(@RequestParam("loginId") String loginId){
        return memberService.verifyDuplicateLoginId(loginId);
    }
}
