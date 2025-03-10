package com.kau.capstone.v2.pet.dto.response;

import com.kau.capstone.entity.pet.Pet;
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
