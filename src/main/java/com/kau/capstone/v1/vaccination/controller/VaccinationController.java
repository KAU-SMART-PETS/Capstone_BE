package com.kau.capstone.v1.vaccination.controller;

import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v1.vaccination.dto.CreateVaccinationRequest;
import com.kau.capstone.v1.vaccination.dto.PutVaccinationRequest;
import com.kau.capstone.v1.vaccination.dto.VaccinationsResponse;
import com.kau.capstone.v1.vaccination.service.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VaccinationController implements VaccinationApi {

    private final VaccinationService vaccinationService;

    @PostMapping("/api/v1/pets/{petId}/vaccination")
    public ResponseEntity<Void> createVaccinationInfoForPet(@LoginUser LoginInfo loginInfo,
                                                            @PathVariable Long petId,
                                                            @RequestBody CreateVaccinationRequest request) {
        vaccinationService.createVaccinationInfo(petId, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/v1/pets/{petId}/vaccination")
    public ResponseEntity<VaccinationsResponse> getVaccinationInfoForPet(@PathVariable Long petId) {
        VaccinationsResponse response = vaccinationService.getVaccinationInfo(petId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/v1/pets/{petId}/vaccination/{vaccinationId}")
    public ResponseEntity<Void> putVaccinationInfoForPet(@LoginUser LoginInfo loginInfo,
                                                         @PathVariable Long petId,
                                                         @PathVariable Long vaccinationId,
                                                         @RequestBody PutVaccinationRequest request) {
        vaccinationService.putVaccinationInfo(vaccinationId, request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/v1/pets/{petId}/vaccination/{vaccinationId}")
    public ResponseEntity<Void> deleteVaccinationInfoForPet(@LoginUser LoginInfo loginInfo,
                                                            @PathVariable Long petId,
                                                            @PathVariable Long vaccinationId) {
        vaccinationService.deleteVaccinationInfo(vaccinationId);

        return ResponseEntity.noContent().build();
    }
}
