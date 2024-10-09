package com.kau.capstone.domain.hospital.dto;

import com.kau.capstone.domain.hospital.entity.Hospital;
import lombok.Builder;

@Builder
public record HospitalResponse(
        String name,
        String address,
        String telephone,
        Double mapX,
        Double mapY
) {

    public static HospitalResponse toResponse(Hospital hospital) {
        return HospitalResponse.builder()
                .name(hospital.getName())
                .address(hospital.getAddress())
                .telephone(hospital.getTelephone())
                .mapX(hospital.getMapX())
                .mapY(hospital.getMapY())
                .build();
    }
}
