package com.kau.capstone.domain.point.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.point.dto.EarnPointRequest;
import com.kau.capstone.domain.point.dto.HistoryResponse;
import com.kau.capstone.domain.point.dto.PayPointRequest;
import com.kau.capstone.domain.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointController implements PointApi {

    private final PointService pointService;

    @PatchMapping("/api/v1/points/payment")
    public ResponseEntity<Void> payWithPoints(@LoginUser LoginInfo loginInfo,
                                              @RequestBody PayPointRequest request) {
        pointService.processPointPayment(loginInfo.memberId(), request);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/v1/points/deposit")
    public ResponseEntity<Void> depositWithPoints(@LoginUser LoginInfo loginInfo,
                                                  @RequestBody EarnPointRequest request) {
        pointService.processPointEarn(loginInfo.memberId(), request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/points")
    public ResponseEntity<HistoryResponse> getPointHistory(@LoginUser LoginInfo loginInfo) {
        HistoryResponse response = pointService.getPointHistory(loginInfo.memberId());

        return ResponseEntity.ok(response);
    }
}
