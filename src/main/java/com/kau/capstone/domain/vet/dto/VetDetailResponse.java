package com.kau.capstone.domain.vet.dto;

import com.kau.capstone.domain.vet.entity.Vet;
import lombok.Builder;

@Builder
public record VetDetailResponse(
        Long id,
        String name,
        String address,
        String telephone,
        Double vetToMemberDistance,
        Double latitude,
        Double longitude
) {

    public static VetDetailResponse toResponse(Vet vet, Double vetToMemberDistance) {
        return VetDetailResponse.builder()
                .id(vet.getId())
                .name(vet.getName())
                .address(vet.getAddress())
                .telephone(vet.getTelephone())
                .vetToMemberDistance(vetToMemberDistance)
                .latitude(vet.getLatitude())
                .longitude(vet.getLongitude())
                .build();
    }
}
