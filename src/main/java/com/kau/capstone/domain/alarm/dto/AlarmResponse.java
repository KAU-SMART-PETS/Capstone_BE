package com.kau.capstone.domain.alarm.dto;

import com.kau.capstone.domain.alarm.entity.Alarm;
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
