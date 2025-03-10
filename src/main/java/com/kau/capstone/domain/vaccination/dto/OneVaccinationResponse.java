package com.kau.capstone.domain.vaccination.dto;

import com.kau.capstone.domain.vaccination.entity.Vaccination;

public record OneVaccinationResponse(
        Long id,
        String name,
        Integer year,
        Integer month,
        Integer day
) {

    public static OneVaccinationResponse toResponse(Vaccination vaccination) {
        return new OneVaccinationResponse(
                vaccination.getId(),
                vaccination.getName(),
                vaccination.getVaccinatedAt().getYear(),
                vaccination.getVaccinatedAt().getMonthValue(),
                vaccination.getVaccinatedAt().getDayOfMonth()
        );
    }
}
