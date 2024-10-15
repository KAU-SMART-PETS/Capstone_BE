package com.kau.capstone.domain.point.repository;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.point.entity.history.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT h FROM History h WHERE h.member = :member")
    List<History> findHistoriesByMember(@Param("member") Member member);
}
