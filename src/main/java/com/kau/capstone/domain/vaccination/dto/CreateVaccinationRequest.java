package com.kau.capstone.domain.vaccination.dto;

public record CreateVaccinationRequest(
        String name,
        Integer year,
        Integer month,
        Integer day
) {
}
