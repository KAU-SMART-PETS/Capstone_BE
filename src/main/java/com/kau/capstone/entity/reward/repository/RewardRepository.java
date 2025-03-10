package com.kau.capstone.entity.reward.repository;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.reward.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    @Query("SELECT r FROM Reward r WHERE r.member = :member ORDER BY r.type ASC")
    List<Reward> findRewardsByMember(@Param("member") Member member);

    @Query("SELECT r FROM Reward r WHERE r.member = :member AND r.type = :type")
    Reward findRewardByMemberAndType(@Param("member") Member member,
                                     @Param("type") Long type);
}
