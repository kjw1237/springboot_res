package kr.co.team.res.service.web;

import kr.co.team.res.common.exceptions.ValidCustomException;
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

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService extends _BaseService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean memberInsert(MemberVO memberVo,
                           StoreVO storeVo) throws ValidCustomException {
        if(memberRepository.existsByLoginId(memberVo.getLoginId())) {
            System.out.println("중복된 아이디 있음");
            return false;
        } else {
            try {
                Account account = new Account();
                account.setLoginId(memberVo.getLoginId());
                account.setPwd(passwordEncoder.encode(memberVo.getPwd()));
                account.setName(memberVo.getName());
                account.setNickName(memberVo.getNickName());
                account.setBirthDate(LocalDateTime.now());
                account.setJoinPlaform(memberVo.getJoinPlatform());
                account.setJoinDate(LocalDateTime.now());
                account.setDelYn("N");
                memberRepository.save(account);

                Store store = new Store();
                store.setMberPid(memberRepository.save(account).getId());
                store.setStoreName(storeVo.getStoreName());
                store.setStoreAddres(storeVo.getStoreAddres());
                store.setStoreCategory(storeVo.getStoreCategory());
                store.setOpenTime(LocalDateTime.now());     // 추후 변경예정
                store.setCloseTime(LocalDateTime.now());    // 추후 변경예정
                store.setStoreDescription(storeVo.getStoreDescription());
                storeRepository.save(store);

                System.out.println("중복된 아이디 없음");
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

}
