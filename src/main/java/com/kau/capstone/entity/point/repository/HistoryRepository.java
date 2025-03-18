package com.kau.capstone.entity.point.repository;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.point.History;
import com.kau.capstone.v2.point.dto.response.HistoryListResV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT h FROM History h WHERE h.point.member = :member ORDER BY h.id DESC ")
    List<History> findHistoriesByMember(@Param("member") Member member);

    default HistoryListResV2 findHistoryListByMember(Member member){
        List<History> res = findHistoriesByMember(member);
        return HistoryListResV2.toResponse(res);
    }
}
