package com.kau.capstone.v1.walk.controller;

import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v1.walk.dto.request.WalkRequest;
import com.kau.capstone.v1.walk.dto.response.WalkDailySummaryResponse;
import com.kau.capstone.v1.walk.dto.response.WalkMonthlyResponse;
import com.kau.capstone.v1.walk.dto.response.WalkRecentDataListResponse;
import com.kau.capstone.v1.walk.dto.response.WalkResponse;
import com.kau.capstone.v1.walk.dto.response.WalkWeeklySummaryListResponse;
import com.kau.capstone.v1.walk.service.WalkService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/walk")
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    // 산책 기록하기
    @PostMapping("/register/{petId}")
    public ResponseEntity<WalkResponse> registerWalk(@RequestBody @Valid WalkRequest walkData,
        @LoginUser LoginInfo loginUser,
        @PathVariable Long petId) {
        WalkResponse response = walkService.saveWalkData(walkData, loginUser.memberId(), petId);
        return ResponseEntity.ok(response);
    }

    // 최근 산책 기록 가져오기
    @GetMapping("/recent")
    public ResponseEntity<WalkRecentDataListResponse> latestWalk(@LoginUser LoginInfo loginUser) {
        WalkRecentDataListResponse response = walkService.getWalkRecentData(loginUser.memberId());
        return ResponseEntity.ok(response);
    }

    // 일간 산책 기록 가져오기
    @GetMapping("/daily/{petId}")
    public ResponseEntity<WalkDailySummaryResponse> dailyWalk(@LoginUser LoginInfo loginUser,
        @PathVariable Long petId,
        @RequestParam LocalDate date) {

        WalkDailySummaryResponse response = walkService.getDailySummary(loginUser.memberId(), petId,
            date);
        return ResponseEntity.ok(response);
    }

    // 월간 산책 기록일
    @GetMapping("/monthly/{petId}")
    public ResponseEntity<WalkMonthlyResponse> monthlyWalk(@LoginUser LoginInfo loginUser,
        @PathVariable Long petId,
        @RequestParam int year,
        @RequestParam int month) {

        WalkMonthlyResponse response = walkService.getMonthlyWalkData(loginUser.memberId(), petId,
            year, month);
        return ResponseEntity.ok(response);

    }

    // 일주일 통계 데이터
    @GetMapping("/weekly/{petId}")
    public ResponseEntity<WalkWeeklySummaryListResponse> weeklyWalk(@LoginUser LoginInfo loginUser,
        @PathVariable Long petId,
        @RequestParam LocalDate date) {
        WalkWeeklySummaryListResponse response = walkService.getWeeklySummaryList(
            loginUser.memberId(), petId, date);
        return ResponseEntity.ok(response);
    }

}
