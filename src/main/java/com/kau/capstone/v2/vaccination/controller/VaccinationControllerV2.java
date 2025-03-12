package com.kau.capstone.v2.vaccination.controller;

import com.kau.capstone._core.dto.ApiResponse;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v2.vaccination.dto.CreateVaccinationReqV2;
import com.kau.capstone.v2.vaccination.dto.PutVaccinationReqV2;
import com.kau.capstone.v2.vaccination.dto.VaccinationsResV2;
import com.kau.capstone.v2.vaccination.service.VaccinationServiceV2;
import lombok.RequiredArgsConstructor;
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
        @RequestBody CreateVaccinationReqV2 request
    ) {
        vaccinationService.createVaccinationInfo(petId, request);
        return ApiResponse.create();
    }

    @GetMapping("/api/v2/pets/{petId}/vaccination")
    public ResponseEntity<ApiResponse<VaccinationsResV2>> getVaccinationInfoForPet(
        @PathVariable Long petId
    ) {
        VaccinationsResV2 response = vaccinationService.getVaccinationInfo(petId);
        return ApiResponse.ok(response);
    }

    @PutMapping("/api/v2/pets/{petId}/vaccination/{vaccinationId}")
    public ResponseEntity<ApiResponse<Void>> putVaccinationInfoForPet(
        @LoginUser LoginInfo loginInfo,
        @PathVariable Long petId,
        @PathVariable Long vaccinationId,
        @RequestBody PutVaccinationReqV2 request
    ) {
        vaccinationService.putVaccinationInfo(vaccinationId, request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/api/v2/pets/{petId}/vaccination/{vaccinationId}")
    public ResponseEntity<ApiResponse<Void>> deleteVaccinationInfoForPet(
        @LoginUser LoginInfo loginInfo,
        @PathVariable Long petId,
        @PathVariable Long vaccinationId
    ) {
        vaccinationService.deleteVaccinationInfo(vaccinationId);
        return ApiResponse.ok();
    }

}
