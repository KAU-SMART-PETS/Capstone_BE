package com.kau.capstone.v2.vaccination.dto;

import java.time.LocalDate;

public record CreateVaccinationReqV2(
    String name,
    LocalDate vaccinatedAt
) {

}
