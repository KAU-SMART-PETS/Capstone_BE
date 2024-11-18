package com.kau.capstone.domain.walk.controller;

import com.kau.capstone.domain.walk.dto.request.WalkRequest;
import com.kau.capstone.domain.walk.dto.response.WalkDailySummaryResponse;
import com.kau.capstone.domain.walk.dto.response.WalkRecentDataListResponse;
import com.kau.capstone.domain.walk.dto.response.WalkRecentDataResponse;
import com.kau.capstone.domain.walk.dto.response.WalkResponse;
import com.kau.capstone.domain.walk.service.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/walk")
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    // 산책 기록하기
    @PostMapping("/end/{platformId}/{petName}")
    public ResponseEntity<WalkResponse> endWalk(@RequestBody WalkRequest walkData, @PathVariable String platformId, @PathVariable String petName) {
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
    @GetMapping("/daily")
    public ResponseEntity<WalkDailySummaryResponse> DailySummary(@RequestParam Long petId,
                                                                 @RequestParam Date date) {

        WalkDailySummaryResponse response = walkService.getDailySummary(petId, date);
        return ResponseEntity.ok(response);
    }

    // 월간 산책 기록일

    // 일주일 통계 데이터 
}
