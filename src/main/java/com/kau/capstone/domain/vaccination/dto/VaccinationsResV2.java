package com.kau.capstone.domain.vaccination.dto;

import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.vaccination.entity.Vaccination;

import java.util.List;

public record VaccinationsResV2(
    PetVaccinationResV2 pet,
    List<OneVaccinationResV2> vaccination
) {

    public static VaccinationsResV2 of(Pet pet, List<Vaccination> vaccinations) {
        PetVaccinationResV2 petVaccinationResponse = PetVaccinationResV2.of(pet);
        List<OneVaccinationResV2> oneVaccinationResponses = vaccinations.stream()
            .map(OneVaccinationResV2::of)
            .toList();

        return new VaccinationsResV2(
            petVaccinationResponse,
            oneVaccinationResponses
        );
    }

}
