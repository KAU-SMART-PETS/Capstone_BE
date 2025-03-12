package com.kau.capstone.v1.reward.controller;

import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v1.point.service.PointService;
import com.kau.capstone.v1.reward.dto.RewardsResponse;
import com.kau.capstone.v1.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RewardController implements RewardApi {

    private final RewardService rewardService;
    private final PointService pointService;

    @GetMapping("/api/v1/rewards")
    public ResponseEntity<RewardsResponse> getRewardsInfo(@LoginUser LoginInfo loginInfo) {
        RewardsResponse response = rewardService.getRewardsInfo(loginInfo.memberId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/v1/rewards/{rewardId}/points/deposit")
    public ResponseEntity<Void> earnRewardWithPoints(@LoginUser LoginInfo loginInfo,
                                                     @PathVariable Long rewardId) {
        pointService.processPointEarnForReward(loginInfo.memberId(), rewardId);

        return ResponseEntity.ok().build();
    }
}
