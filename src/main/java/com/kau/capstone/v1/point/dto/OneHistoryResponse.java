package com.kau.capstone.v1.point.dto;

import com.kau.capstone.entity.point.History;
import lombok.Builder;

import java.time.format.DateTimeFormatter;

public record OneHistoryResponse(
        Long id,
        Long totalPoint,
        Long changePoint,
        String name,
        String date
) {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static OneHistoryResponse toResponse(History history) {
        return new OneHistoryResponse(
                history.getId(),
                history.getTotalPoint(),
                history.getChangePoint(),
                history.getName(),
                DateTimeFormatter.ofPattern(DATE_FORMAT).format(history.getDate())
        );
    }
}
