package com.kau.capstone.v1.vaccination.dto;

import java.time.LocalDate;

public record PutVaccinationReqV2(
    String name,
    LocalDate vaccinatedAt
) {

}
