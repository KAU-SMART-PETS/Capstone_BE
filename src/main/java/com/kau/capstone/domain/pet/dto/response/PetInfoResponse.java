package com.kau.capstone.domain.pet.dto.response;

import com.kau.capstone.global.common.Gender;
import com.kau.capstone.domain.pet.entity.PetType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PetInfoResponse {

    private String name;

    private PetType petType;

    private Gender gender;

    private float weight;

    private String imageUrl;

    private Integer age;
}