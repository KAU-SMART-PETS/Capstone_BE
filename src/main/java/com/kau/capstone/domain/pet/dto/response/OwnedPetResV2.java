package com.kau.capstone.domain.pet.dto.response;

import com.kau.capstone.domain.pet.entity.Pet;

public record OwnedPetResV2(
    Long id,
    String name,
    String imageUrl
) {

    public static OwnedPetResV2 toResponse(Pet pet) {
        return new OwnedPetResV2(pet.getId(), pet.getName(), pet.getImageUrl());
    }
}