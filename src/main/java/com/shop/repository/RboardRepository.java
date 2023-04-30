package com.shop.repository;

import com.shop.entity.Rboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RboardRepository extends JpaRepository<Rboard, Long> {

    @Modifying
    @Query(value = "update Rboard b set b.rboardHits=b.rboardHits+1 where b.id=:id") // --> Param의 id
    void updateHits(@Param("id") Long id);
}
