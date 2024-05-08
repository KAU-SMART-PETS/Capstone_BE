package com.kau.capstone.domain.pet.controller;

import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.service.PetService;
import com.kau.capstone.global.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pet")
@RequiredArgsConstructor
public class PetRestController {

    private final PetService petService;

    @PostMapping("/regist")
    public ResponseDTO<String> createPetRegist(PetRegistRequest petRegistRequest) {
        petService.registPet(petRegistRequest);
        return ResponseDTO.res("성공적으로 반려동물 정보를 저장하였습니다.");
    }

}
