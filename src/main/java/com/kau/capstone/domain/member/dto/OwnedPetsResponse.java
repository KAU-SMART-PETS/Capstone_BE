package com.kau.capstone.domain.member.dto;

import com.kau.capstone.domain.pet.entity.Pet;
import lombok.Builder;

import java.util.List;

@Builder
public record OwnedPetsResponse(
        List<OwnedPetResponse> pets
) {

    public static OwnedPetsResponse toResponse(List<Pet> pets) {
        List<OwnedPetResponse> ownedPetResponses = pets.stream()
                .map(OwnedPetResponse::toResponse)
                .toList();

        return OwnedPetsResponse.builder()
                .pets(ownedPetResponses)
                .build();
    }
}
