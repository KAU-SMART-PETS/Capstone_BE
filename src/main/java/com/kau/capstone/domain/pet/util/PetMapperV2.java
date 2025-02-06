package com.kau.capstone.domain.pet.util;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import com.kau.capstone.domain.pet.dto.request.PetRegistReqV2;
import com.kau.capstone.domain.pet.entity.Pet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PetMapperV2 {

    public static Pet toPet(PetRegistReqV2 petRegistReq) {
        return Pet.of(petRegistReq);
    }

    public static OwnedPet toOwnedPet(Member member, Pet pet) {
        return OwnedPet.of(member, pet);
    }

}
