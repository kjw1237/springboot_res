package kr.co.team.res.service.web;

import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.common.utill.MessageHandler;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Store;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.StoreRepository;
import kr.co.team.res.domain.vo.MemberVO;
import kr.co.team.res.domain.vo.StoreVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService extends _BaseService  {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean memberInsert(MemberVO memberVo,
                                StoreVO storeVo) throws ValidCustomException {
        if(memberRepository.existsByLoginId(memberVo.getLoginId())) {
            return false;
        } else {
            try {
                Account account = new Account();
                account.setBirthDate(LocalDate.parse(memberVo.getYear() +"-"+
                                     String.format("%02d" , memberVo.getMonth()) +"-"+
                                     String.format("%02d" , memberVo.getDay())
                        , DateTimeFormatter.ISO_DATE));

                account.setJoinPlaform("RES");
                account.setLoginId(memberVo.getLoginId());
                account.setPwd(passwordEncoder.encode(memberVo.getPwd()));
                account.setName(memberVo.getName());
                account.setNickName(memberVo.getNickName());
                account.setJoinPlaform(memberVo.getJoinPlatform());
                account.setJoinDate(LocalDateTime.now());
                account.setDelYn("N");
                memberRepository.save(account);


                Store store = new Store();
                store.setMberPid(memberRepository.save(account).getId());
                store.setStoreName(storeVo.getStoreName());
                store.setStoreAddres(storeVo.getStoreAddres());
                store.setStoreCategory(storeVo.getStoreCategory());
                store.setOpenTime(storeVo.getOpenTime());
                store.setCloseTime(storeVo.getCloseTime());
                store.setStoreDescription(storeVo.getStoreDescription());
                storeRepository.save(store);

                return true;
            } catch (ValidCustomException ve) {
                System.out.println(ve);
                return false;
            } catch (Exception e){
                System.out.println(e);
                return false;
            }
        }
    }


    //DvTy Chk
    public boolean chkDvTy(UserRollType dv){
        if(!UserRollType.NORMAL.equals(dv) && !UserRollType.PARTNERS.equals(dv) && !UserRollType.ADMIN.equals(dv)) {
            return true;
        } else {
            return false;
        }
    }

    //Verify Id
    public boolean verifyDuplicateLoginId(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

    //Verify Email

    public String memberLogin(MemberVO memberVO, HttpSession session, Model model) {
        Account account = memberRepository.findByLoginId(memberVO.getLoginId());

        if(account == null) {
            model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
            return "pages/member/login";
        } else if(passwordEncoder.matches(memberVO.getPwd() , account.getPwd())) {
            Store store = storeRepository.findByMberPid(account.getId());

            session.setAttribute("user", account);
            session.setAttribute("store", store);
            return "redirect:/";
        } else {
            model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
            return "pages/member/login";
        }
    }

    @Transactional
    public void memberUpdate(MemberVO memberVo, StoreVO storeVo, HttpSession session, HttpServletResponse response) {
        Account accountSessionData = (Account) session.getAttribute("user");
        Store storeSessionData = (Store) session.getAttribute("store");
        try {
            Account account = memberRepository.findAccountById(accountSessionData.getId());
            account.setId(accountSessionData.getId());
            account.setName(memberVo.getName());
            account.setBirthDate(LocalDate.parse(memberVo.getYear() +"-"+
                            String.format("%02d" , memberVo.getMonth()) +"-"+
                            String.format("%02d" , memberVo.getDay())
                    , DateTimeFormatter.ISO_DATE));

            Store store = storeRepository.findStoreById(storeSessionData.getId());
            store.setId(storeSessionData.getId());
            store.setStoreName(storeVo.getStoreName());
            store.setStoreAddres(storeVo.getStoreAddres());
            store.setOpenTime(storeVo.getOpenTime());
            store.setCloseTime(storeVo.getCloseTime());
            MessageHandler.alert(response , "정보가 수정되었습니다.");
        } catch (ValidCustomException ve) {
            System.out.println(ve);
            MessageHandler.alertAndBack(response , "정보 수정에 실패하였습니다.");
        } catch (Exception e){
            System.out.println(e);
            MessageHandler.alertAndBack(response , "정보 수정에 실패하였습니다.");
        }
    }

    @Transactional
    public void memberChangePwd(MemberVO memberVO, HttpSession session, HttpServletResponse response) {
        Account accountSessionData = (Account) session.getAttribute("user");
        try {
            Account account = memberRepository.findAccountById(accountSessionData.getId());
            account.setPwd(passwordEncoder.encode(memberVO.getPwd()));
            session.invalidate();

            MessageHandler.alert(response , "비밀번호가 변경되었습니다. 다시 로그인해주세요.");
        } catch (ValidCustomException ve) {
            System.out.println(ve);
            MessageHandler.alertAndBack(response , "비밀번호 변경에 실패하였습니다.");
        } catch (Exception e){
            System.out.println(e);
            MessageHandler.alertAndBack(response , "비밀번호 변경에 실패하였습니다.");
        }
    }
}