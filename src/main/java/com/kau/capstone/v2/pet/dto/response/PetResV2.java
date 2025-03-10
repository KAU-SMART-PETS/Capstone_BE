package com.kau.capstone.v2.pet.dto.response;

import com.kau.capstone.entity.pet.Gender;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.pet.PetType;

public record PetResV2(
    Long id,
    String name,
    PetType petType,
    Gender gender,
    Double weight,
    Integer age,
    String breed,
    String imageUrl
) {
    public static PetResV2 toResponse(Pet pet) {
        return new PetResV2(
            pet.getId(),
            pet.getName(),
            pet.getPetType(),
            pet.getGender(),
            pet.getWeight(),
            pet.getAge(),
            pet.getBreed(),
            pet.getImageUrl()
        );
    }
}
