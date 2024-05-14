package com.kau.capstone.domain.pet.util;

import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.global.common.Gender;
import com.kau.capstone.global.common.PetType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PetMapper {

    public static Pet toPet(PetRegistRequest petRegistRequest) {
        Gender gender = Gender.fromInt(petRegistRequest.getGender());
        PetType petType = PetType.fromInt(petRegistRequest.getPetType());

        return Pet.builder()
            .name(petRegistRequest.getName())
            .petType(petType)
            .gender(gender)
            .age(petRegistRequest.getAge())
            .weight(petRegistRequest.getWeight())
            .imageUrl(petRegistRequest.getImageUrl())
            .build();
    }

}
