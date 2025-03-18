package com.kau.capstone.v2.point.dto.response;

import com.kau.capstone.entity.point.History;

import java.time.format.DateTimeFormatter;
import java.util.List;

public record HistoryListResV2(
        List<HistoryResV2> history
) {
    public static HistoryListResV2 toResponse(List<History> histories) {
        List<HistoryResV2> responses = histories.stream()
                .map(HistoryResV2::toResponse)
                .toList();

        return new HistoryListResV2(responses);
    }

    public record HistoryResV2(
            Long id,
            Long totalPoint,
            Long changePoint,
            String name,
            String date
    ) {

        public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

        public static HistoryResV2 toResponse(History history) {
            return new HistoryResV2(
                history.getId(),
                history.getTotalPoint(),
                history.getChangePoint(),
                history.getName(),
                DateTimeFormatter.ofPattern(DATE_FORMAT).format(history.getDate())
            );
        }
    }
}
