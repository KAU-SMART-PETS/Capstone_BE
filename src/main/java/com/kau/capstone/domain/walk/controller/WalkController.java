package com.kau.capstone.domain.walk.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.walk.dto.request.WalkRequest;
import com.kau.capstone.domain.walk.dto.response.*;
import com.kau.capstone.domain.walk.service.WalkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    @GetMapping("/recent/{memberId}")
    public ResponseEntity<WalkRecentDataListResponse> latestWalk(@PathVariable Long memberId) {
        WalkRecentDataListResponse response = walkService.getWalkRecentData(memberId);
        return ResponseEntity.ok(response);
    }

    // 일간 산책 기록 가져오기
    @GetMapping("/daily/{petId}")
    public ResponseEntity<WalkDailySummaryResponse> dailyWalk(@LoginUser LoginInfo loginUser,
                                                              @PathVariable Long petId,
                                                              @RequestParam LocalDate date) {

        WalkDailySummaryResponse response = walkService.getDailySummary(loginUser.memberId(), petId, date);
        return ResponseEntity.ok(response);
    }

    // 월간 산책 기록일
    @GetMapping("/monthly/{petId}")
    public ResponseEntity<WalkMonthlyResponse> monthlyWalk(@LoginUser LoginInfo loginUser,
                                                           @PathVariable Long petId,
                                                           @RequestParam int year,
                                                           @RequestParam int month) {

        WalkMonthlyResponse response = walkService.getMonthlyWalkData(loginUser.memberId(), petId, year, month);
        return ResponseEntity.ok(response);

    }

    // 일주일 통계 데이터
    @GetMapping("/week/{petId}")
    public ResponseEntity<WalkWeeklySummaryResponse> weeklyWalk(@LoginUser LoginInfo loginUser,
                                                                @PathVariable Long petId,
                                                                @RequestParam LocalDate date) {
        WalkWeeklySummaryResponse response = walkService.getWeeklySummary(loginUser.memberId(), petId, date);
        return ResponseEntity.ok(response);
    }

}
