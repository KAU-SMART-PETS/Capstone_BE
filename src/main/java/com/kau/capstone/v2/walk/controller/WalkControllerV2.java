package com.kau.capstone.v2.walk.controller;

import com.kau.capstone._core.dto.ApiResponse;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v2.walk.dto.request.WalkCreateReqV2;
import com.kau.capstone.v2.walk.dto.response.WalkCreateResV2;
import com.kau.capstone.v2.walk.service.WalkServiceV2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/walk")
@RequiredArgsConstructor
public class WalkControllerV2 {

    private final WalkServiceV2 walkServiceV2;

    // 산책 기록하기
    @PostMapping("/{petId}")
    public ResponseEntity<ApiResponse<WalkCreateResV2>> createWalk(
        @LoginUser LoginInfo loginInfo,
        @PathVariable Long petId,
        @RequestBody @Valid WalkCreateReqV2 walkCreateReqv2
    ) {
        WalkCreateResV2 walkCreateRes = walkServiceV2.createWalk(loginInfo, petId, walkCreateReqv2);
        return ApiResponse.ok(walkCreateRes);
    }

    // 최근 산책 기록 가져오기

    // 일일 산책 기록 가져오기

    // 월간 산책 기록 가져오기

    // 주간 산책 기록 가져오기

}
