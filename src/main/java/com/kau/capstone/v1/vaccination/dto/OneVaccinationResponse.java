package com.kau.capstone.v1.vaccination.dto;

import com.kau.capstone.entity.vaccination.Vaccination;
import lombok.Builder;

@Builder
public record OneVaccinationResponse(
        Long id,
        String name,
        Integer year,
        Integer month,
        Integer day
) {

    public static OneVaccinationResponse toResponse(Vaccination vaccination) {
        return OneVaccinationResponse.builder()
                .id(vaccination.getId())
                .name(vaccination.getName())
                .year(vaccination.getTimeYear())
                .month(vaccination.getTimeMonth())
                .day(vaccination.getTimeDay())
                .build();
    }
}
