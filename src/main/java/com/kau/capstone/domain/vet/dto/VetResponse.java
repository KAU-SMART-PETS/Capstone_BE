package com.kau.capstone.domain.vet.dto;

import com.kau.capstone.domain.vet.entity.Vet;
import lombok.Builder;

@Builder
public record VetResponse(
        Long id,
        String name,
        String address,
        String telephone
) {

    public static VetResponse toResponse(Vet vet) {
        return VetResponse.builder()
                .id(vet.getId())
                .name(vet.getName())
                .address(vet.getAddress())
                .telephone(vet.getTelephone())
                .build();
    }
}
