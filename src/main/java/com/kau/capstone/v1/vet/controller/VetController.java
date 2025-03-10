package com.kau.capstone.v1.vet.controller;

import com.kau.capstone.v1.vet.dto.MemberLocationRequest;
import com.kau.capstone.v1.vet.dto.VetDetailResponse;
import com.kau.capstone.v1.vet.dto.VetsResponse;
import com.kau.capstone.v1.vet.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VetController implements VetApi {

    private final VetService vetService;

    @PostMapping(path = "/api/v1/vets/{vetId}")
    public ResponseEntity<VetDetailResponse> getVetDetailInfo(@PathVariable Long vetId,
                                                              @RequestBody MemberLocationRequest memberLocationRequest) {
        VetDetailResponse response = vetService.getVetDetailInfo(vetId, memberLocationRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/api/v1/vets")
    public ResponseEntity<VetsResponse> getVetsInfo(@RequestBody MemberLocationRequest memberLocationRequest) {
        VetsResponse response = vetService.getVetsInfo(memberLocationRequest);

        return ResponseEntity.ok(response);
    }
}
