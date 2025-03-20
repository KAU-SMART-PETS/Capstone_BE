package com.kau.capstone.entity.reward.repository;

import com.kau.capstone.entity.reward.Reward;
import com.kau.capstone.v2.reward.exception.RewardNotFoundExceptionV2;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    List<Reward> findAll();

    default Reward getById(long id) {
        return findById(id).orElseThrow(RewardNotFoundExceptionV2::new);
    }
}