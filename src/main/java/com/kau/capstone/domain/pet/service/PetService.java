package com.kau.capstone.domain.pet.service;

import com.kau.capstone.domain.pet.controller.PetRestController;
import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.pet.util.PetMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    public void registPet(PetRegistRequest petRegistRequest){
        petRepository.save(PetMapper.toPet(petRegistRequest));
    }
}
