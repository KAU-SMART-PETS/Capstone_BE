package com.kau.capstone.v1.vaccination.dto;

import com.kau.capstone.entity.pet.Pet;
import lombok.Builder;

@Builder
public record PetVaccinationResponse(
        String name
) {

    public static PetVaccinationResponse toResponse(Pet pet) {
        return PetVaccinationResponse.builder()
                .name(pet.getName())
                .build();
    }
}
