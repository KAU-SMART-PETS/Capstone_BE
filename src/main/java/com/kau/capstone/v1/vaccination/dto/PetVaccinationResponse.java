package com.kau.capstone.v1.vaccination.dto;

import com.kau.capstone.entity.pet.Pet;

public record PetVaccinationResponse(
        String name
) {

    public static PetVaccinationResponse toResponse(Pet pet) {
        return new PetVaccinationResponse(pet.getName());
    }
}
