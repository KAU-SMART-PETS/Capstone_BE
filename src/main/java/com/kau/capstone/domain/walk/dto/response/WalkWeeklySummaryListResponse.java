package com.kau.capstone.domain.walk.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalkWeeklySummaryListResponse {
    private List<WalkDailySummaryResponse> dailySummaries;
}
