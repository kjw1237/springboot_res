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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String loginpage() { return "pages/member/login"; }

    @RequestMapping("/pages/choiceregister")
    public String registerchoice(){ return "pages/choice_register"; }

    @PostMapping("/api/member/insert2")
    public ResponseEntity insert2(MemberVO memberVo,
                                  StoreVO storeVo,
                                  Errors errors) {
        System.out.println("아이디 : " + memberVo.getLoginId());
        System.out.println("비밀번호 : " + memberVo.getPwd());
        System.out.println("비밀번호확인 : " + memberVo.getPwdChk());
        System.out.println("이름 : " + memberVo.getName());
        System.out.println("닉네임 : " + memberVo.getNickName());
        System.out.println("생년월일 : " + memberVo.getBirthdate());
        System.out.println("가입플랫폼 : " + memberVo.getJoinPlatform());
        System.out.println("매장이름 : " + storeVo.getStoreName());
        System.out.println("매장 주소 : " + storeVo.getStoreAddres());
        System.out.println("업종 : " + storeVo.getStoreCategory());
        System.out.println("오픈 시간 : " + storeVo.getOpenTime());
        System.out.println("종료 시간 : " + storeVo.getCloseTime());
        System.out.println("매장 설명 : " + storeVo.getStoreDescription());

        boolean result = false;
        String msg = "";

        memberService.insert2(memberVo, storeVo);

        if(result) {
            msg = "회원가입이 완료되었습니다.";
            return ResponseEntity.ok(msg);
        } else {
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

}
