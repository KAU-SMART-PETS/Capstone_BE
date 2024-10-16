package com.kau.capstone.domain.vaccination.dto;

import com.kau.capstone.domain.pet.entity.Pet;
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
