package com.kau.capstone.domain.vaccination.controller;

import com.kau.capstone._core.dto.ApiResponse;
import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.vaccination.dto.CreateVaccinationRequestV2;
import com.kau.capstone.domain.vaccination.dto.PutVaccinationRequest;
import com.kau.capstone.domain.vaccination.dto.VaccinationsResponse;
import com.kau.capstone.domain.vaccination.service.VaccinationServiceV2;
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
public class VaccinationControllerV2 implements VaccinationApiV2 {

    private final VaccinationServiceV2 vaccinationService;

    @PostMapping("/api/v2/pets/{petId}/vaccination")
    public ResponseEntity<ApiResponse<Void>> createVaccinationInfoForPet(
        @LoginUser LoginInfo loginInfo,
        @PathVariable Long petId,
        @RequestBody CreateVaccinationRequestV2 request
    ) {
        vaccinationService.createVaccinationInfo(petId, request);
        return ApiResponse.create();
    }

//    @GetMapping("/api/v1/pets/{petId}/vaccination")
//    public ResponseEntity<VaccinationsResponse> getVaccinationInfoForPet(@PathVariable Long petId) {
//        VaccinationsResponse response = vaccinationService.getVaccinationInfo(petId);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PutMapping("/api/v1/pets/{petId}/vaccination/{vaccinationId}")
//    public ResponseEntity<Void> putVaccinationInfoForPet(@LoginUser LoginInfo loginInfo,
//        @PathVariable Long petId,
//        @PathVariable Long vaccinationId,
//        @RequestBody PutVaccinationRequest request) {
//        vaccinationService.putVaccinationInfo(vaccinationId, request);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/api/v1/pets/{petId}/vaccination/{vaccinationId}")
//    public ResponseEntity<Void> deleteVaccinationInfoForPet(@LoginUser LoginInfo loginInfo,
//        @PathVariable Long petId,
//        @PathVariable Long vaccinationId) {
//        vaccinationService.deleteVaccinationInfo(vaccinationId);
//
//        return ResponseEntity.noContent().build();
//    }
}
