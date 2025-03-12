package com.kau.capstone.v1.alarm.dto;

import com.kau.capstone.entity.alarm.Alarm;
import lombok.Builder;

import java.util.List;

@Builder
public record AlarmResponse(
        List<OneAlarmResponse> alarm
) {

    public static AlarmResponse toResponse(List<Alarm> alarms) {
        List<OneAlarmResponse> responses = alarms.stream()
                .map(OneAlarmResponse::toResponse)
                .toList();

        return AlarmResponse.builder()
                .alarm(responses)
                .build();
    }
}
