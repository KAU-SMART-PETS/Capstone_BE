package com.kau.capstone.v2.reward.controller;

import com.kau.capstone._core.dto.ApiResponse;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v2.reward.dto.RewardCreateReqV2;
import com.kau.capstone.v2.reward.service.RewardServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

}
