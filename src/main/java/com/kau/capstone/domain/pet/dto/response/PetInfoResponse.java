package com.kau.capstone.domain.pet.dto.response;

import com.kau.capstone.domain.pet.entity.Gender;
import com.kau.capstone.domain.pet.entity.PetType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

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