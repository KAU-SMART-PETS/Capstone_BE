package com.kau.capstone.v2.vaccination.dto;

import java.time.LocalDate;

public record PutVaccinationReqV2(
    String name,
    LocalDate vaccinatedAt
) {

}
