package com.kau.capstone.domain.vaccination.dto;

import com.kau.capstone.domain.pet.entity.Pet;

public record PetVaccinationResV2(
    String name
) {

    public static PetVaccinationResV2 of(Pet pet) {
        return new PetVaccinationResV2(
            pet.getName()
        );
    }
}
