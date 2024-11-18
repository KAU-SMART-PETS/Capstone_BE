package com.kau.capstone.domain.walk.controller;

import com.kau.capstone.domain.walk.dto.request.WalkRequest;
import com.kau.capstone.domain.walk.dto.response.*;
import com.kau.capstone.domain.walk.service.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/walk")
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    // 산책 기록하기
    @PostMapping("/register/{platformId}/{petName}")
    public ResponseEntity<WalkResponse> registerWalk(@RequestBody WalkRequest walkData,
                                                     @PathVariable String platformId,
                                                     @PathVariable String petName) {
        WalkResponse response = walkService.saveWalkData(walkData, platformId, petName);
        return ResponseEntity.ok(response);
    }

    // 최근 산책 기록 가져오기
    @GetMapping("/latest/{platformId}")
    public ResponseEntity<WalkRecentDataListResponse> latestWalk(@PathVariable String platformId) {
        WalkRecentDataListResponse response = walkService.getWalkRecentData(platformId);
        return ResponseEntity.ok(response);
    }

    // 일간 산책 기록 가져오기
    @GetMapping("/daily/{platformId}/{petName}")
    public ResponseEntity<WalkDailySummaryResponse> dailyWalk(@PathVariable String platformId,
                                                              @PathVariable String petName,
                                                              @RequestParam LocalDate date) {

        WalkDailySummaryResponse response = walkService.getDailySummary(platformId, petName, date);
        return ResponseEntity.ok(response);
    }

    // 월간 산책 기록일
    @GetMapping("/monthly/{platformId}/{petName}")
    public ResponseEntity<WalkMonthlyResponse> monthlyWalk(@PathVariable String platformId,
                                                           @PathVariable String petName,
                                                           @RequestParam int year,
                                                           @RequestParam int month) {

        WalkMonthlyResponse response = walkService.getMonthlyWalkData(platformId, petName, year, month);
        return ResponseEntity.ok(response);

    }

    // 일주일 통계 데이터
    @GetMapping("/week/{platformId}/{petName}")
    public ResponseEntity<WalkWeeklySummaryResponse> weeklyWalk(@PathVariable String platformId,
                                                                @PathVariable String petName,
                                                                @RequestParam LocalDate date) {
        WalkWeeklySummaryResponse response = walkService.getWeeklySummary(platformId, petName, date);
        return ResponseEntity.ok(response);
    }

}
