package com.kau.capstone.v1.point.dto;

import com.kau.capstone.entity.point.History;
import lombok.Builder;

import java.util.List;

public record HistoryResponse(
        List<OneHistoryResponse> history
) {

    public static HistoryResponse toResponse(List<History> histories) {
        List<OneHistoryResponse> responses = histories.stream()
                .map(OneHistoryResponse::toResponse)
                .toList();

        return new HistoryResponse(responses);
    }
}
