package com.kau.capstone.domain.food.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.food.dto.FoodsResponse;
import com.kau.capstone.domain.point.dto.DeliveryFeeRequest;
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
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "사료 API")
public interface FoodApi {

    @Operation(summary = "사료 목록 조회", description = "전체 사료 목록입니다")
    @ApiResponse(
            responseCode = "200",
            description = "사료 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = FoodsResponse.class))
    )
    @GetMapping("/api/v1/foods")
    ResponseEntity<FoodsResponse> getFoodsInfo();

    @Operation(summary = "포인트로 사료 결제", description = "유저가 가지고 있는 포인트로 사료를 결제합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "사료 결제 성공"
    )
    @PostMapping("/api/v1/foods/{foodId}/points/payment")
    ResponseEntity<Void> payFoodWithPoints(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo,
            @Parameter(description = "사료 ID")
            @PathVariable Long foodId,
            @Parameter(description = "배달료")
            @RequestBody DeliveryFeeRequest request
    );
}
