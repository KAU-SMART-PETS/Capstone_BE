package com.kau.capstone.domain.pet.controller;

import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/pet")
public class PetRestController {

    private final PetService petService;

    @PostMapping("/regist")
    public ResponseEntity<String> createPetRegist(@RequestBody PetRegistRequest petRegistRequest) {
        petService.registPet(petRegistRequest);
        return ResponseEntity.ok().build();
    }

}
