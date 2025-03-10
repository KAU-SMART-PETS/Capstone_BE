package com.kau.capstone.domain.pet.dto.response;

import com.kau.capstone.domain.pet.entity.Pet;
import java.util.List;

public record OwnedPetsResV2(
    List<OwnedPetResV2> pets
) {
    public static OwnedPetsResV2 toResponse(List<Pet> pets){
        List<OwnedPetResV2> responses = pets.stream()
            .map(OwnedPetResV2::toResponse)
            .toList();

        return new OwnedPetsResV2(responses);
    }

}
