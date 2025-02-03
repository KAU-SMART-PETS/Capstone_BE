package com.kau.capstone.domain.pet.util;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import com.kau.capstone.domain.pet.dto.request.PetRegistReqV2;
import com.kau.capstone.domain.pet.dto.response.PetResV2;
import com.kau.capstone.domain.pet.entity.Pet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PetMapperV2 {

    public static Pet toPet(PetRegistReqV2 petRegistReq) {
        return new Pet(petRegistReq.getName(), petRegistReq.getPetType(),
            petRegistReq.getGender(), petRegistReq.getWeight(),
            petRegistReq.getAge(), petRegistReq.getBreed());
    }

    public static OwnedPet toOwnedPet(Member member, Pet pet){
        return new OwnedPet(member, pet);
    }

    public static PetResV2 toPetResV2Dto(Pet pet){
        return new PetResV2(pet.getName(), pet.getPetType(),
            pet.getGender(), pet.getWeight(), pet.getAge(),
            pet.getBreed(), pet.getImageUrl());
    }


}
