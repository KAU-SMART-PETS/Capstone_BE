package com.kau.capstone.domain.vaccination.dto;

import com.kau.capstone.domain.pet.entity.Pet;

public record PetVaccinationResponse(
        String name
) {

    public static PetVaccinationResponse toResponse(Pet pet) {
        return new PetVaccinationResponse(pet.getName());
    }
}
