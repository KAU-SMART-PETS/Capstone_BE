package com.kau.capstone.domain.vaccination.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.vaccination.dto.VaccinationsResponse;
import com.kau.capstone.domain.vaccination.service.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @GetMapping("/api/v1/pets/{petId}/vaccination")
    public ResponseEntity<VaccinationsResponse> getVaccinationInfoForPet(@PathVariable Long petId) {
        VaccinationsResponse response = vaccinationService.getVaccinationInfo(petId);

        return ResponseEntity.ok(response);
    }
}
