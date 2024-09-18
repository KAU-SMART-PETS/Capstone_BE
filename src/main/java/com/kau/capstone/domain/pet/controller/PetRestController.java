package com.kau.capstone.domain.pet.controller;

import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.dto.request.UpdatePetInfoRequest;
import com.kau.capstone.domain.pet.dto.response.PetInfoResponse;
import com.kau.capstone.domain.pet.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/pet")
public class PetRestController {

    private final PetService petService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "반려동물 정보 등록 API", description = "반려동물 정보를 등록하는 API입니다.")
    public ResponseEntity<String> createPetInfo(
        @RequestBody PetRegistRequest petRegistRequest
    ) {
        petService.createPetInfo(petRegistRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("반려동물 정보를 성공적으로 저장하였습니다.");
    }

    @GetMapping("/{pet_id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "반려동물 정보 조회 API", description = "반려동물 정보를 조회하는 API입니다.")
    public ResponseEntity<PetInfoResponse> getPetInfo(
        @PathVariable Long petId
    ) {
        PetInfoResponse petInfoResponse = petService.getPetInfo(petId);
        return ResponseEntity.status(HttpStatus.OK).body(petInfoResponse);
    }

    @PatchMapping("/{pet_id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "반려동물 정보 수정 API", description = "반려동물 정보를 수정하는 API입니다.")
    public ResponseEntity<?> updatePetInfo(
        @PathVariable("pet_id") @NotNull Long petId,
        @RequestBody UpdatePetInfoRequest updatePetInfoRequest
    ) {
        petService.updatePetInfo(petId, updatePetInfoRequest);
        return ResponseEntity.status(HttpStatus.OK).body("반려동물 정보를 성공적으로 수정하였습니다.");
    }

    @DeleteMapping("/{pet_id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "반려동물 정보 삭제 API", description = "반려동물 정보를 삭제하는 API입니다.")
    public ResponseEntity<?> deletePetInfo(
        @PathVariable("pet_id") @NotNull Long petId
    ) {
        petService.deletePetInfo(petId);
        return ResponseEntity.status(HttpStatus.OK).body("반려동물 정보를 성공적으로 삭제하였습니다.");
    }

}
