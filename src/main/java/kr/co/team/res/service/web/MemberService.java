package kr.co.team.res.service.web;

import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.common.utill.DateFormatHandler;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Store;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.StoreRepository;
import kr.co.team.res.domain.vo.MemberVO;
import kr.co.team.res.domain.vo.StoreVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.session.StandardSessionFacade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
                DateFormatHandler handler = new DateFormatHandler();
                MemberVO vo = handler.ParseDate(account.getBirthDate());
                account.setBirthDate(LocalDate.parse(memberVo.getYear() +"-"+
                                     String.format("%02s" , memberVo.getMonth()) +"-"+
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

    public String memberLogin(MemberVO memberVO, HttpSession session) {
        Account account = memberRepository.findByLoginId(memberVO.getLoginId());

        if(passwordEncoder.matches(memberVO.getPwd() , account.getPwd())) {
            Store store = storeRepository.findByMberPid(account.getId());

            session.setAttribute("user", account);
            session.setAttribute("store", store);
            return "redirect:/";
        } else {
            return "pages/member/login";
        }
    }

}