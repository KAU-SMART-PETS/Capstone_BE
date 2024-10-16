package com.kau.capstone.domain.vaccination.dto;

import com.kau.capstone.domain.vaccination.entity.Vaccination;
import lombok.Builder;

@Builder
public record OneVaccinationResponse(
        String name,
        Integer year,
        Integer month,
        Integer day
) {

    public static OneVaccinationResponse toResponse(Vaccination vaccination) {
        return OneVaccinationResponse.builder()
                .name(vaccination.getName())
                .year(vaccination.getTimeYear())
                .month(vaccination.getTimeMonth())
                .day(vaccination.getTimeDay())
                .build();
    }
}
