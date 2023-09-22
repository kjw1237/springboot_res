package kr.co.team.res.service.web;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Partners;
import kr.co.team.res.domain.entity.Store;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.PartnersRepository;
import kr.co.team.res.domain.repository.StoreRepository;
import kr.co.team.res.domain.vo.MemberVO;
import kr.co.team.res.domain.vo.StoreVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final PartnersRepository partnersRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean insert2(MemberVO memberVo,
                           StoreVO storeVo) throws ValidCustomException {

        try {
            Account account = new Account();
            Store store = new Store();

            account.setLoginId(memberVo.getLoginId());
            account.setPwd(memberVo.getPwd());
            account.setName(memberVo.getName());
            account.setNickName(memberVo.getNickName());
            account.setBirthDate(LocalDateTime.now());
            account.setJoinPlaform(memberVo.getJoinPlatform());
            account.setJoinDate(LocalDateTime.now());
            account.setDelYn("N");
            memberRepository.save(account);

            store.setMberPid(memberRepository.save(account).getId());
            store.setStoreName(storeVo.getStoreName());
            store.setStoreAddres(storeVo.getStoreAddres());
            store.setStoreCategory(storeVo.getStoreCategory());
            store.setOpenTime(LocalDateTime.now());     // 추후 변경예정
            store.setCloseTime(LocalDateTime.now());    // 추후 변경예정
            store.setStoreDescription(storeVo.getStoreDescription());

            storeRepository.save(store);

            return true;
        } catch (ValidCustomException ve) {
            return false;
        } catch (Exception e){
            return false;
        }
    }

    public boolean insert(MemberVO memberVO) throws ValidCustomException {

        try {
            // verifyDuplicateLoginId(memberVO.getLoginId());

            Account account = new Account();
            Partners partners = new Partners();

            //Controller에서 인증로직 수행 후 Service에서 인증로직 검토 -> insert 수행

//            account.setDelAt("N");
//            account.setApproval("Y");
//            account.setRegDtm(LocalDateTime.now());
//            account.setLoginId(memberVO.getLoginId());
//            account.setPwd(passwordEncoder.encode(memberVO.getPwd()));
//            account.setNm(memberVO.getNm());
//            account.setNcnm(memberVO.getNcnm());
//            account.setMoblphon(memberVO.getMoblphon());
//            account.setEmail(memberVO.getEmail());
//            account.setBrthdy(memberVO.getBrthdy());
//            account.setAdres(memberVO.getAdres());
//            account.setDtlAdres(memberVO.getDtlAdres());
//            account.setSexPrTy(memberVO.getSexPrTy());

            //RollType 구분 vo SET
            if(memberVO.getMberDvTy().equals(UserRollType.NORMAL)){
                account.setId(account.getId());
                // account.setMberDvTy(UserRollType.NORMAL);
//                memberRepository.save(account);

            } else if(memberVO.getMberDvTy().equals(UserRollType.PARTNERS)){
                //Partners Table 추가 정보 입력.

                // account.setMberDvTy(UserRollType.PARTNERS);
//                memberRepository.save(account);
            }

            return true;
        } catch (ValidCustomException ve) {
            return false;
        } catch (Exception e){
            return false;
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
//    public void verifyDuplicateLoginId(String loginId) {
//        if(memberRepository.findByLoginId(loginId).isPresent()) {
//            throw new ValidCustomException("이미 사용 중인 아이디입니다." , "loginId");
//        }
//    }

    //Verify Email

}
