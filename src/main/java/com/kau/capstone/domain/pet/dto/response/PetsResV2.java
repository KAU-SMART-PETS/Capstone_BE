package com.kau.capstone.domain.pet.dto.response;

import lombok.AllArgsConstructor;

public record PetsResV2(
    Long id,
    String name,
    String imageUrl
) {

}
