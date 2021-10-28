package com.rsupport.notice.domain.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query("SELECT p FROM Notice p ORDER BY p.id DESC ")
    List<Notice> findAllDesc();

    @Modifying
    @Query("UPDATE Notice p set p.hit = p.hit + 1 WHERE p.id = :id")
    void updateHit(@Param("id") Long id);
}
