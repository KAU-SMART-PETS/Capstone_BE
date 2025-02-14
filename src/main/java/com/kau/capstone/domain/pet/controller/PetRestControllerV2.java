package com.kau.capstone.domain.pet.controller;

import com.kau.capstone._core.dto.ApiResponse;
import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.pet.dto.request.PetRegistReqV2;
import com.kau.capstone.domain.pet.dto.request.PetReqV2;
import com.kau.capstone.domain.pet.dto.response.OwnedPetsResV2;
import com.kau.capstone.domain.pet.dto.response.PetResV2;
import com.kau.capstone.domain.pet.service.PetServiceV2;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v2/pets")
@RequiredArgsConstructor
public class PetRestControllerV2 {

    private final PetServiceV2 petService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<String>> createPetInfo(
        @LoginUser LoginInfo loginInfo,
        @Valid @ModelAttribute("petRegistReq") PetRegistReqV2 petRegistReq
    ) throws IOException {
        petService.createPetInfo(loginInfo, petRegistReq);
        return ApiResponse.ok("생성 완료");
    }

    @GetMapping("/{pet_id}")
    public ResponseEntity<ApiResponse<PetResV2>> getPetInfo(
        @LoginUser LoginInfo loginInfo,
        @PathVariable("pet_id") @NotNull Long petId
    ) {
        PetResV2 petRes = petService.getPetInfo(loginInfo, petId);
        return ApiResponse.ok(petRes);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<OwnedPetsResV2>> getPetsInfo(
        @LoginUser LoginInfo loginInfo
    ) {
        OwnedPetsResV2 pets = petService.getPetsInfo(loginInfo);
        return ApiResponse.ok(pets);
    }

    @PatchMapping("/{pet_id}")
    public ResponseEntity<ApiResponse<String>> updatePetInfo(
        @LoginUser LoginInfo loginInfo,
        @PathVariable("pet_id") @NotNull Long petId,
        @Valid @RequestBody PetReqV2 petRequest
    ) {
        petService.updatePetInfo(loginInfo, petId, petRequest);
        return ApiResponse.ok("수정 완료");
    }

    @PatchMapping("/{pet_id}/images")
    public ResponseEntity<ApiResponse<String>> updatePetImage(
        @LoginUser LoginInfo loginInfo,
        @PathVariable("pet_id") @NotNull Long petId,
        @RequestParam MultipartFile image
    ) throws IOException {
        petService.updatePetImage(loginInfo, petId, image);
        return ApiResponse.ok("이미지 수정 완료");
    }

    @DeleteMapping("/{pet_id}")
    public ResponseEntity<ApiResponse<String>> deletePet(
        @LoginUser LoginInfo loginInfo,
        @PathVariable("pet_id") @NotNull Long petId
    ) {
        petService.deletePet(loginInfo, petId);
        return ApiResponse.ok("삭제 완료");

    }


}