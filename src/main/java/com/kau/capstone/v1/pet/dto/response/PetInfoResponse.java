package com.kau.capstone.v1.pet.dto.response;

import com.kau.capstone.entity.pet.Gender;
import com.kau.capstone.entity.pet.PetType;
import lombok.Builder;

public record PetInfoResponse (
    String name,
    PetType petType,
    Gender gender,
    double weight,
    String imageUrl,
    Integer age
){

    @Builder
    public PetInfoResponse {
    }
}