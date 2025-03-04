package com.kau.capstone.domain.vaccination.dto;

import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.vaccination.entity.Vaccination;

import java.util.List;

public record VaccinationsResponseV2(
    PetVaccinationResponseV2 pet,
    List<OneVaccinationResponseV2> vaccination
) {

    public static VaccinationsResponseV2 of(Pet pet, List<Vaccination> vaccinations) {
        PetVaccinationResponseV2 petVaccinationResponse = PetVaccinationResponseV2.of(pet);
        List<OneVaccinationResponseV2> oneVaccinationResponses = vaccinations.stream()
            .map(OneVaccinationResponseV2::of)
            .toList();

        return new VaccinationsResponseV2(
            petVaccinationResponse,
            oneVaccinationResponses
        );
    }

}
