package com.kau.capstone.domain.point.dto;

import com.kau.capstone.domain.point.entity.history.History;
import lombok.Builder;

import java.time.format.DateTimeFormatter;

@Builder
public record OneHistoryResponse(
        Long id,
        Long totalPoint,
        Long changePoint,
        String name,
        String date
) {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static OneHistoryResponse toResponse(History history) {
        return OneHistoryResponse.builder()
                .id(history.getId())
                .totalPoint(history.getTotalPoint())
                .changePoint(history.getChangePoint())
                .name(history.getName())
                .date(DateTimeFormatter.ofPattern(DATE_FORMAT).format(history.getDate()))
                .build();
    }
}
