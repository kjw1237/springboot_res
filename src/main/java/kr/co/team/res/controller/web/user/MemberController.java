package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.Base;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.vo.MemberVO;
import kr.co.team.res.domain.vo.StoreVO;
import kr.co.team.res.service.web.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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


    @PostMapping("/api/member/insert2")
    public ResponseEntity insert2(MemberVO memberVo,
                                  StoreVO storeVo,
                                  Errors errors) {

        boolean result = false;
        String msg = "";

        memberService.insert2(memberVo , storeVo);

        System.out.println(result);
        System.out.println(memberService.insert2(memberVo , storeVo));

        if(result) {
            msg = "회원가입이 완료되었습니다.";
            return ResponseEntity.ok(msg);
        } else {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }
    }

    @PostMapping("/api/member/insert")
    public ResponseEntity insert(MemberVO memberVO ,
                                 Errors errors ,
                                 BindingResult bindingResult) {

        boolean result = false;
        String msg = "";
        //hidden 값 잘 받아냄.

        if(memberVO.getAuthMobileChk() == 2 && memberVO.getAuthMobileChk() == 2) {
            if(memberVO.getAuthMobileChk() == 2){
                memberVO.setMobileAttcAt("Y");
                memberVO.setEmailAttcAt("N");
                memberVO.setMobileAttcDtm(LocalDateTime.now());
                memberService.insert(memberVO);
            } else if (memberVO.getAuthEmailChk() == 2) {
                memberVO.setEmailAttcAt("Y");
                memberVO.setMobileAttcAt("N");
                memberVO.setEmailAttcDtm(LocalDateTime.now());
                memberService.insert(memberVO);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }

        if(result){
            msg = "회원가입이 완료되었습니다.";
            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }
    }

    @PostMapping("/api/member/verifyduplicateloginid")
    @ResponseBody
    public boolean verifyDuplicateLoginId(@RequestParam("loginId") String loginId){
        return memberService.verifyDuplicateLoginId(loginId);
    }
}
