package com.kau.capstone.domain.pet.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.pet.dto.request.PetRegistReqV2;
import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.dto.request.PetReqV2;
import com.kau.capstone.domain.pet.dto.response.PetInfoResponse;
import com.kau.capstone.domain.pet.dto.response.PetResV2;
import com.kau.capstone.domain.pet.dto.response.PetsResV2;
import com.kau.capstone.domain.pet.service.PetServiceV2;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/pets")
public class PetRestControllerV2 {
    private final PetServiceV2 petService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createPetInfo(
        @LoginUser LoginInfo loginInfo,
        @Valid @ModelAttribute("petRegistReq") PetRegistReqV2 petRegistReq
    ) throws IOException {
        petService.createPetInfo(loginInfo, petRegistReq);
        return ResponseEntity.status(HttpStatus.CREATED).body("반려동물 정보를 성공적으로 저장하였습니다.");
    }

    @GetMapping("/{pet_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PetResV2> getPetInfo(
        @LoginUser LoginInfo loginInfo,
        @PathVariable("pet_id") Long petId
    ){
        PetResV2 petRes = petService.getPetInfo(loginInfo, petId);
        return ResponseEntity.status(HttpStatus.OK).body(petRes);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PetsResV2>> getPetsInfo(
        @LoginUser LoginInfo loginInfo
    ){
        List<PetsResV2> pets = petService.getPetsInfo(loginInfo);
        return ResponseEntity.status(HttpStatus.OK).body(pets);
    }

    @PatchMapping("/{pet_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updatePetInfo(
        @LoginUser LoginInfo loginInfo,
        @PathVariable("pet_id") @NotNull Long petId,
        @Valid @RequestBody PetReqV2 petRequest
    ) {
        petService.updatePetInfo(loginInfo, petId, petRequest);
        return ResponseEntity.status(HttpStatus.OK).body("반려동물 정보를 성공적으로 수정하였습니다.");
    }


}