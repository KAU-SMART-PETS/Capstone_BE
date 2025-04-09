package com.kau.capstone.v2.point.controller;

import com.kau.capstone._core.dto.ApiResponse;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v2.point.dto.request.DepositPointReqV2;
import com.kau.capstone.v2.point.dto.request.PayPointReqV2;
import com.kau.capstone.v2.point.dto.response.HistoryListResV2;
import com.kau.capstone.v2.point.service.PointServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/point")
public class PointControllerV2 {
    private final PointServiceV2 pointServiceV2;

    @PatchMapping("/api/v2/points/payment")
    public ResponseEntity<ApiResponse<Void>> payPoints(
            @LoginUser LoginInfo loginInfo,
            @RequestBody PayPointReqV2 request
    ) {
        pointServiceV2.payPoint(loginInfo.memberId(), request);
        return ApiResponse.ok();
    }

    @PatchMapping("/api/v2/points/deposit")
    public ResponseEntity<ApiResponse<Void>> depositPoints(
            @LoginUser LoginInfo loginInfo,
            @RequestBody DepositPointReqV2 request
    ) {
        pointServiceV2.depositPoint(loginInfo.memberId(), request);
        return ApiResponse.ok();
    }

    @GetMapping("/api/v2/points")
    public ResponseEntity<ApiResponse<HistoryListResV2>> getPointHistory(
            @LoginUser LoginInfo loginInfo
    ) {
        HistoryListResV2 res = pointServiceV2.getPointHistory(loginInfo.memberId());
        return ApiResponse.ok(res);
    }
}
