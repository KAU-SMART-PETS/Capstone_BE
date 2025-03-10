package com.kau.capstone.v1.reward.controller;

import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v1.reward.dto.RewardsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "리워드 API")
public interface RewardApi {

    @Operation(summary = "리워드 목록 조회", description = "사용자의 리워드 목록을 조회하는 기능입니다.")
    @ApiResponse(
            responseCode = "200",
            description = "리워드 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = RewardsResponse.class))
    )
    @GetMapping("/api/v1/rewards")
    ResponseEntity<RewardsResponse> getRewardsInfo(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo
    );

    @Operation(summary = "리워드 달성으로 포인트 적립", description = "사용자가 리워드를 달성해서 적립하는 포인트입니다.")
    @ApiResponse(
            responseCode = "200",
            description = "리워드 포인트 적립 성공"
    )
    @PostMapping("/api/v1/rewards/{rewardId}/points/deposit")
    ResponseEntity<Void> earnRewardWithPoints(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo,
            @Parameter(description = "리워드 ID")
            @PathVariable Long rewardId
    );
}
