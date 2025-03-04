package com.kau.capstone.domain.vaccination.dto;

import java.time.LocalDate;

public record CreateVaccinationReqV2(
    String name,
    LocalDate vaccinatedAt
) {

}
