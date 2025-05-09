package com.kau.capstone.v2.vaccination.dto;

import com.kau.capstone.entity.vaccination.Vaccination;
import java.time.LocalDate;

public record OneVaccinationResV2(
    Long id,
    String name,
    LocalDate vaccinatedAt
) {

    public static OneVaccinationResV2 of(Vaccination vaccination) {
        return new OneVaccinationResV2(
            vaccination.getId(),
            vaccination.getName(),
            vaccination.getVaccinatedAt()
        );
    }
}
