package com.kau.capstone.domain.hospital.controller;

import com.kau.capstone.domain.hospital.dto.HospitalResponse;
import com.kau.capstone.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping(path = "/api/v1/vet/{vet_id}")
    public ResponseEntity<HospitalResponse> getHospitalInfo(Long hospitalId) {
        HospitalResponse response = hospitalService.getHospitalInfo(hospitalId);

        return ResponseEntity.ok(response);
    }
}
