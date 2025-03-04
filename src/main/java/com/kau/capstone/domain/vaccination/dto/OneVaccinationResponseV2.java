package com.kau.capstone.domain.vaccination.dto;

import com.kau.capstone.domain.vaccination.entity.Vaccination;
import java.time.LocalDate;

public record OneVaccinationResponseV2(
    Long id,
    String name,
    LocalDate vaccinatedAt
) {

    public static OneVaccinationResponseV2 of(Vaccination vaccination) {
        return new OneVaccinationResponseV2(
            vaccination.getId(),
            vaccination.getName(),
            vaccination.getVaccinatedAt()
        );
    }
}
