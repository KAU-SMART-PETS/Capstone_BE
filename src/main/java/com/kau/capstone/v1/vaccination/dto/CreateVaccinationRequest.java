package com.kau.capstone.v1.vaccination.dto;

public record CreateVaccinationRequest(
        String name,
        Integer year,
        Integer month,
        Integer day
) {
}
