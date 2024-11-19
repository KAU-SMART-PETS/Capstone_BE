package com.kau.capstone.domain.walk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class WalkMonthlyResponse {
    private List<LocalDate> walkDates;
}
