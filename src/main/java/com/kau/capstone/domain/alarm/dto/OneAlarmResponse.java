package com.kau.capstone.domain.alarm.dto;

import com.kau.capstone.domain.alarm.entity.Alarm;
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
