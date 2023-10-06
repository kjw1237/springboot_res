package kr.co.team.res.controller.web.user;

import javassist.compiler.Parser;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@RequiredArgsConstructor
public class MemberController extends Base{
    private final MemberService memberService;

    @RequestMapping("/member/register")
    public String registerpage() { return "pages/member/register"; }


    @PostMapping("/api/member/memberInsert")
    public String memberInsert(MemberVO memberVo , StoreVO storeVo)  {

        boolean result = memberService.memberInsert(memberVo , storeVo);

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
