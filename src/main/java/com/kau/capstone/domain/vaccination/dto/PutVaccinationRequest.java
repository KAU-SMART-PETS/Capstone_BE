package com.kau.capstone.domain.vaccination.dto;

public record PutVaccinationRequest(
        String name,
        Integer year,
        Integer month,
        Integer day
) {
}
