package com.kau.capstone.domain.member.dto;

import com.kau.capstone.domain.pet.entity.Pet;
import lombok.Builder;

@Builder
public record OwnedPetResponse(
        Long id,
        String name,
        String imageUrl
) {

    public static OwnedPetResponse toResponse(Pet pet) {
        return OwnedPetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .imageUrl(pet.getImageUrl())
                .build();
    }
}
