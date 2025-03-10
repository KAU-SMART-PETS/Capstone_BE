package com.kau.capstone.v1.vaccination.dto;

public record PutVaccinationRequest(
        String name,
        Integer year,
        Integer month,
        Integer day
) {
}
