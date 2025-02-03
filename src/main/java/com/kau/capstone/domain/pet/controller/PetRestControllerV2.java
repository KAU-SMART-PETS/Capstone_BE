package com.kau.capstone.domain.pet.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.pet.dto.request.PetRegistReqV2;
import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.service.PetService;
import com.kau.capstone.domain.pet.service.PetServiceV2;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
}