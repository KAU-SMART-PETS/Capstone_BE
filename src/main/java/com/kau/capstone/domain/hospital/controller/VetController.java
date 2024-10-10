package com.kau.capstone.domain.hospital.controller;

import com.kau.capstone.domain.hospital.dto.VetResponse;
import com.kau.capstone.domain.hospital.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping(path = "/api/v1/vet/{vet_id}")
    public ResponseEntity<VetResponse> getVetInfo(Long vetId) {
        VetResponse response = vetService.getVetInfo(vetId);

        return ResponseEntity.ok(response);
    }
}
