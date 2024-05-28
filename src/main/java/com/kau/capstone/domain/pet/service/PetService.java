package com.kau.capstone.domain.pet.service;

import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.dto.response.PetInfoResponse;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.exception.PetNotFoundException;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.pet.util.PetMapper;
import com.kau.capstone.global.exception.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    @Transactional
    public void registPet(PetRegistRequest petRegistRequest) {
        petRepository.save(PetMapper.toPet(petRegistRequest));
    }

    public Pet findByPetId(Long petId) {
        Optional<Pet> petOptional = petRepository.findById(petId);

        // 만약 post 가 존재하지 않으면 ErrorStatus.POST_NOT_FOUND 반환.
        return petOptional.orElseThrow(
            () -> new PetNotFoundException(ErrorCode.PET_INFO_NOT_FOUND));
    }

    public PetInfoResponse getPetInfo(Long petId) {

        Pet pet = findByPetId(petId);

        return PetMapper.toGetResponseDTO(pet);
    }
}
