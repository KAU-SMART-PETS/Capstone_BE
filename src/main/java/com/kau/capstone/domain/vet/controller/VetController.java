package com.kau.capstone.domain.vet.controller;

import com.kau.capstone.domain.vet.dto.VetResponse;
import com.kau.capstone.domain.vet.dto.VetsResponse;
import com.kau.capstone.domain.vet.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping(path = "/api/v1/vets/{vet_id}")
    public ResponseEntity<VetResponse> getVetInfo(Long vetId) {
        VetResponse response = vetService.getVetInfo(vetId);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/api/v1/vets")
    public ResponseEntity<VetsResponse>  getVetsInfo() {
        VetsResponse response = vetService.getVetsInfo();

        return ResponseEntity.ok(response);
    }
}
