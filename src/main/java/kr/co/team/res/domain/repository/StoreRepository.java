package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByMberPid(Long mberPid);
}