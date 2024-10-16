package com.kau.capstone.domain.reward.repository;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.point.entity.history.History;
import com.kau.capstone.domain.reward.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    @Query("SELECT r FROM Reward r WHERE r.member = :member ORDER BY r.type ASC")
    List<Reward> findRewardsByMember(@Param("member") Member member);
}
