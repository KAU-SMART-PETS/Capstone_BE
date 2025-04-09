package com.kau.capstone.v1.member.dto;

import com.kau.capstone.entity.pet.Pet;
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
