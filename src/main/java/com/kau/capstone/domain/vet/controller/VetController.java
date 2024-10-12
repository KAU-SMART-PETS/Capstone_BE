package com.kau.capstone.domain.vet.controller;

import com.kau.capstone.domain.vet.dto.MemberLocationRequest;
import com.kau.capstone.domain.vet.dto.VetDetailResponse;
import com.kau.capstone.domain.vet.dto.VetsResponse;
import com.kau.capstone.domain.vet.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VetController implements VetApi{

    private final VetService vetService;

    @GetMapping(path = "/api/v1/vets/{vetId}")
    public ResponseEntity<VetDetailResponse> getVetInfo(@PathVariable Long vetId,
                                                        @RequestBody MemberLocationRequest memberLocationRequest) {
        VetDetailResponse response = vetService.getVetInfo(vetId, memberLocationRequest);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/api/v1/vets")
    public ResponseEntity<VetsResponse> getVetsInfo() {
        VetsResponse response = vetService.getVetsInfo();

        return ResponseEntity.ok(response);
    }
}
