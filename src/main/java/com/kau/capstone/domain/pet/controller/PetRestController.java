package com.kau.capstone.domain.pet.controller;

import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.dto.response.PetInfoResponse;
import com.kau.capstone.domain.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{pet_id}")
    public ResponseEntity<PetInfoResponse> getPetInfo(@PathVariable Long pet_id) {
        petService.getPetInfo(pet_id);
        return new ResponseEntity<>(petService.getPetInfo(pet_id), HttpStatus.OK);
    }

}
