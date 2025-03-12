package com.kau.capstone.v2.vaccination.dto;

import com.kau.capstone.entity.pet.Pet;

public record PetVaccinationResV2(
    String name
) {

    public static PetVaccinationResV2 of(Pet pet) {
        return new PetVaccinationResV2(
            pet.getName()
        );
    }
}
