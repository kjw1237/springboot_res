package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Test, Long> {
    Account findAllByLoginIdAndDelAt(String loginId , String delAt);

    Optional<Account> findByLoginId(String loginId);

    Optional<Account> findByEmail(String email);



}
