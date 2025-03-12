package com.kau.capstone.v2.pet.util;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.OwnedPet;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.v2.pet.dto.request.PetRegistReqV2;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PetMapperV2 {

    public static Pet toPet(PetRegistReqV2 petRegistReq, Member member) {
        return Pet.of(petRegistReq, member);
    }

    public static OwnedPet toOwnedPet(Member member, Pet pet) {
        return OwnedPet.of(member, pet);
    }

}
