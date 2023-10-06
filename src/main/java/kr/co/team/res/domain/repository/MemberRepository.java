package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Account, Long> {
//    Account findAllByLoginIdAndDelAt(String loginId , String delAt);
//
    boolean existsByLoginId(String loginId);

    Account findByLoginId(String loginId);
}
