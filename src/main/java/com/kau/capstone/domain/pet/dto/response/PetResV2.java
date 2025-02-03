package com.kau.capstone.domain.pet.dto.response;

import com.kau.capstone.domain.pet.entity.Gender;
import com.kau.capstone.domain.pet.entity.PetType;

public record PetResV2(
    String name,
    PetType petType,
    Gender gender,
    Double weight,
    Integer age,
    String breed,
    String imageUrl
) {

}
