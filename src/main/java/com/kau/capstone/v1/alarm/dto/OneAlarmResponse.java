package com.kau.capstone.v1.alarm.dto;

import com.kau.capstone.entity.alarm.Alarm;
import lombok.Builder;

@Builder
public record OneAlarmResponse(
        Long id,
        String text
) {

    public static OneAlarmResponse toResponse(Alarm alarm) {
        return OneAlarmResponse.builder()
                .id(alarm.getId())
                .text(alarm.getText())
                .build();
    }
}
