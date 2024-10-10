package com.kau.capstone.domain.hospital.dto;

import com.kau.capstone.domain.hospital.entity.Vet;
import lombok.Builder;

@Builder
public record VetResponse(
        String name,
        String address,
        String telephone,
        Double mapX,
        Double mapY
) {

    public static VetResponse toResponse(Vet vet) {
        return VetResponse.builder()
                .name(vet.getName())
                .address(vet.getAddress())
                .telephone(vet.getTelephone())
                .mapX(vet.getMapX())
                .mapY(vet.getMapY())
                .build();
    }
}
