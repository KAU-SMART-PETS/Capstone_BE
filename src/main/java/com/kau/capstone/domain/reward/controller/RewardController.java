package com.kau.capstone.domain.reward.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.reward.dto.RewardsResponse;
import com.kau.capstone.domain.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    @GetMapping("/api/v1/rewards")
    public ResponseEntity<RewardsResponse> getRewardsInfo(@LoginUser LoginInfo loginInfo) {
        RewardsResponse response = rewardService.getRewardsInfo(loginInfo.memberId());

        return ResponseEntity.ok(response);
    }
}
