package com.kau.capstone.domain.vaccination.dto;

import com.kau.capstone.domain.pet.entity.Pet;

public record PetVaccinationResponseV2(
    String name
) {

    public static PetVaccinationResponseV2 of(Pet pet) {
        return new PetVaccinationResponseV2(
            pet.getName()
        );
    }
}
