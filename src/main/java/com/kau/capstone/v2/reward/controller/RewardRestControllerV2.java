package com.kau.capstone.v2.reward.controller;

import com.kau.capstone._core.dto.ApiResponse;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v2.reward.dto.RewardCreateReqV2;
import com.kau.capstone.v2.reward.dto.RewardResV2;
import com.kau.capstone.v2.reward.dto.RewardUpdateReqV2;
import com.kau.capstone.v2.reward.dto.RewardsResV2;
import com.kau.capstone.v2.reward.service.RewardServiceV2;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/rewards")
@RequiredArgsConstructor
public class RewardRestControllerV2 {

    private final RewardServiceV2 rewardService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<String>> createReward(
        @LoginUser LoginInfo loginInfo,
        @RequestBody RewardCreateReqV2 rewardCreateReq
    ) {
        rewardService.createReward(loginInfo, rewardCreateReq);
        return ApiResponse.ok();
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<RewardsResV2>> getRewards(
        @LoginUser LoginInfo loginInfo
    ) {
        RewardsResV2 rewards = rewardService.getRewards(loginInfo);
        if (Objects.isNull(rewards)) {
            return ApiResponse.of(HttpStatus.NO_CONTENT, "등록된 리워드가 없습니다.", null);
        }

        return ApiResponse.ok(rewards);
    }

    @GetMapping("/{rewardId}")
    public ResponseEntity<ApiResponse<RewardResV2>> getReward(
        @LoginUser LoginInfo loginInfo,
        @PathVariable long rewardId
    ) {
        RewardResV2 reward = rewardService.getReward(loginInfo, rewardId);
        return ApiResponse.ok(reward);
    }

    @PutMapping("/{rewardId}")
    public ResponseEntity<ApiResponse<String>> updateReward(
        @LoginUser LoginInfo loginInfo,
        @PathVariable long rewardId,
        @RequestBody RewardUpdateReqV2 request
    ) {
        rewardService.updateReward(loginInfo, rewardId, request);
        return ApiResponse.ok();
    }

}
