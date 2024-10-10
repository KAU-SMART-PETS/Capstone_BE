package com.kau.capstone.domain.vet.dto;

import com.kau.capstone.domain.vet.entity.Vet;
import lombok.Builder;

import java.util.List;

@Builder
public record VetsResponse(
        List<VetResponse> vets
) {
    public static VetsResponse toResponse(List<Vet> vets) {
        List<VetResponse> responses = vets.stream()
                .map(VetResponse::toResponse)
                .toList();

        return VetsResponse.builder()
                .vets(responses)
                .build();
    }
}
