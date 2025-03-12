package com.kau.capstone.v1.alarm.controller;

import com.kau.capstone.v1.alarm.dto.AlarmResponse;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "알람 API")
public interface AlarmApi {

    @Operation(summary = "알람 목록 조회", description = "유저에게 보여지는 알람 목록입니다.")
    @ApiResponse(
            responseCode = "200",
            description = "알람 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = AlarmResponse.class))
    )
    @GetMapping("/api/v1/alarm")
    ResponseEntity<AlarmResponse> getAlarmInfo(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo
    );
}
