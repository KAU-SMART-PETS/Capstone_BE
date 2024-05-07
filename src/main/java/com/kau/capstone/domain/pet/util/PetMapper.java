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
        Gender gender = Gender.fromInt(petRegistRequest.gender());
        PetType petType = PetType.fromInt(petRegistRequest.gender());

        return Pet.builder()
            .name(petRegistRequest.name())
            .petType(petType)
            .gender(gender)
            .age(petRegistRequest.age())
            .weight(petRegistRequest.weight())
            .imageUrl(petRegistRequest.imageUrl())
            .build();
    }

}
