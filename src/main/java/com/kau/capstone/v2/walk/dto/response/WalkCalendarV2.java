package com.kau.capstone.v2.walk.dto.response;

import java.time.LocalDate;

public record WalkCalendarV2(
    LocalDate walkDates
) {
    public static WalkCalendarV2 of(LocalDate walkDates) {
        return new WalkCalendarV2(walkDates);
    }
}
