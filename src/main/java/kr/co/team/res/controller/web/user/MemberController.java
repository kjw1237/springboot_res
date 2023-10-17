package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.Base;
import kr.co.team.res.domain.vo.MemberVO;
import kr.co.team.res.domain.vo.StoreVO;
import kr.co.team.res.service.web.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class MemberController extends Base{
    private final MemberService memberService;

    @RequestMapping("/member/register")
    public String registerpage() { return "pages/member/register"; }


    @PostMapping("/api/member/memberInsert")
    public String memberInsert(MemberVO memberVo , StoreVO storeVo) {

        boolean result = memberService.memberInsert(memberVo , storeVo);

        if(result) {
            return "pages/member/register_success";
        } else {
            return "pages/member/register";
        }
    }

    @PostMapping("/api/member/verifyduplicateloginid")
    @ResponseBody
    public boolean verifyDuplicateLoginId(@RequestParam("loginId") String loginId) {
        return memberService.verifyDuplicateLoginId(loginId);
    }

    @GetMapping("/member/profile")
    public String memberProfile() { return "pages/member/profile"; }

    @PostMapping("/api/member/memberUpdate")
    public void memberUpdate(MemberVO memberVO, StoreVO storeVO, HttpSession session, HttpServletResponse response) {
        memberService.memberUpdate(memberVO, storeVO, session, response);
    }

    @PostMapping("/api/member//changePwd")
    public void changePwd(MemberVO memberVO, HttpSession session, HttpServletResponse response) {
        memberService.memberChangePwd(memberVO, session, response);
    }

}
