package com.kau.capstone.v1.vet.dto;

import com.kau.capstone.entity.vet.Vet;
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
