package com.kau.capstone.domain.walk.controller;

import com.kau.capstone.domain.walk.dto.request.WalkRequest;
import com.kau.capstone.domain.walk.dto.response.WalkRecentDataListResponse;
import com.kau.capstone.domain.walk.dto.response.WalkRecentDataResponse;
import com.kau.capstone.domain.walk.dto.response.WalkResponse;
import com.kau.capstone.domain.walk.service.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/walk")
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    // 산책 기록하기
    @PostMapping("/end")
    public ResponseEntity<WalkResponse> endWalk(@RequestBody WalkRequest walkData) {
        WalkResponse response = walkService.saveWalkData(walkData);
        return ResponseEntity.ok(response);
    }

    // 최근 산책 기록 가져오기
    @GetMapping("/latest/{member_id}")
    public ResponseEntity<WalkRecentDataListResponse> latestWalk(@PathVariable Long member_id) {
        WalkRecentDataListResponse response = walkService.getWalkRecentData(member_id);
        return ResponseEntity.ok(response);
    }

    // 일간 산책 기록 가져오기


    //
}
