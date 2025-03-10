package com.kau.capstone.v2.pet.dto.response;

import com.kau.capstone.entity.pet.Pet;

public record OwnedPetResV2(
    Long id,
    String name,
    String imageUrl
) {

    public static OwnedPetResV2 toResponse(Pet pet) {
        return new OwnedPetResV2(pet.getId(), pet.getName(), pet.getImageUrl());
    }
}