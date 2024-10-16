package com.kau.capstone.domain.point.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.point.dto.EarnPointRequest;
import com.kau.capstone.domain.point.dto.HistoryResponse;
import com.kau.capstone.domain.point.dto.PayPointRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "포인트 API")
public interface PointApi {

    @Operation(summary = "[사용X] 단순 포인트 결제", description = "포인트 결제하는 기능입니다. 테스트 용도로만 사용하세요")
    @ApiResponse(
            responseCode = "200",
            description = "포인트 결제 성공"
    )
    @PatchMapping("/api/v1/points/payment")
    ResponseEntity<Void> payWithPoints(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo,
            @Parameter(description = "결제에 필요한 포인트")
            @RequestBody PayPointRequest request
    );

    @Operation(summary = "[사용X] 포인트 적립", description = "포인트 적립 기능입니다. 테스트 용도로만 사용하세요")
    @ApiResponse(
            responseCode = "200",
            description = "포인트 적립 성공"
    )
    @PatchMapping("/api/v1/points/deposit")
    ResponseEntity<Void> depositWithPoints(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo,
            @Parameter(description = "획득 포인트")
            @RequestBody EarnPointRequest request
    );

    @Operation(summary = "포인트 적립/결제 내역 조회", description = "포인트 내역 조회 기능입니다.")
    @ApiResponse(
            responseCode = "200",
            description = "포인트 내역 조회 성공",
            content = @Content(schema = @Schema(implementation = HistoryResponse.class))
    )
    @GetMapping("/api/v1/points")
    ResponseEntity<HistoryResponse> getPointHistory(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo
    );
}
