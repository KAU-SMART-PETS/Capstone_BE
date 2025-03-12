package com.kau.capstone.v1.alarm.controller;

import com.kau.capstone.v1.alarm.dto.AlarmResponse;
import com.kau.capstone.v1.alarm.service.AlarmService;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlarmController implements AlarmApi {

    private final AlarmService alarmService;

    @GetMapping("/api/v1/alarm")
    public ResponseEntity<AlarmResponse> getAlarmInfo(@LoginUser LoginInfo loginInfo) {
        AlarmResponse response = alarmService.getAlarmInfo(loginInfo.memberId());

        return ResponseEntity.ok(response);
    }
}
