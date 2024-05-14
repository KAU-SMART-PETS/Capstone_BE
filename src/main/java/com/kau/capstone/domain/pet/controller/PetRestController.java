package com.kau.capstone.domain.pet.controller;

import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.service.PetService;
import com.kau.capstone.global.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pet")
@RequiredArgsConstructor
public class PetRestController {

    private final PetService petService;

    @PostMapping("/regist")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createPetRegist(PetRegistRequest petRegistRequest) {
        petService.registPet(petRegistRequest);
        return ResponseEntity.ok().build();
    }

}
