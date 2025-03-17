package com.kau.capstone.entity.reward.repository;

import com.kau.capstone.entity.reward.Reward;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    @Query("SELECT r FROM Reward r")
    List<Reward> findRewards();
}